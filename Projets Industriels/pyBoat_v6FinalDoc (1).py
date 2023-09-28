"""
Librairie pour l'interface gui.
"""
from guizero import App, Text, PushButton
import time
import board
"""
2 librairies pour l'utilisation du capteur BNO055.
"""
import busio
import adafruit_bno055
"""
Librairie pour utilisation des pins Raspberry pi.
"""
import RPi.GPIO as GPIO

"""
Setup de la configuration du gpio du raspberry.
"""
GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)

"""
Pins pour les leds pour voir dans quelle direction on va.
"""
goRight=23
goLeft=22

"""
Pins pour le moteur.
"""
M1_En =12
M1_In1 =20
M1_In2 =21

"""
Activation des pins  pour le moteur.
"""
GPIO.setup(M1_En,GPIO.OUT)
GPIO.output(M1_En, GPIO.HIGH)
GPIO.setup(M1_In1,GPIO.OUT)
GPIO.setup(M1_In2,GPIO.OUT)
"""
Setup de la vitesse du moteur.
"""
M1_vitesse=GPIO.PWM(M1_En,100)
M1_vitesse.start(100)

GPIO.setmode(GPIO.BCM)

"""
Activation des pins associés aux leds de direction
"""
GPIO.setup(goLeft,GPIO.OUT)
GPIO.setup(goRight,GPIO.OUT)

"""
Est à True si un des boutons est activé.
"""
buttonOn = False
"""
Setup de l'angle pour prévenir les déviations et rediriger le bateau vers la position de départ.
"""
angle=0
"""
Setup du bno055 en communication i2c pour pouvoir utiliser le capteur.
"""
i2c=board.I2C()
sensor=adafruit_bno055.BNO055_I2C(i2c)
direction=""

"""
Cette fonction sert à aller vers la gauche quand on appuye sur le bouton Left de lécran
puis il réinitialise le capteur à 0 pour prendre en compte le déplacement.
Nous avons utilisé le init(), et pas le reset() car le reset remet le bus_device à 0 et crée un erreur,
alors que init reset et setup le bus_device.
"""
def testLeft():
    global buttonOn
    global goLeft
    global sensor
    buttonOn = True
    GPIO.output(M1_In2, GPIO.LOW)
    GPIO.output(M1_In1, GPIO.HIGH)
    time.sleep(1)
    GPIO.output(M1_In1, GPIO.LOW)
    buttonOn = False
    sensor=adafruit_bno055.BNO055_I2C(i2c)

"""
Cette fonction sert à aller vers la gauche quand on appuye sur le bouton Right de l'écran,
puis il réinitialise le capteur à 0 pour prendre en compte le déplacement.
"""
def testRight():
    global buttonOn
    global goRight
    global sensor
    buttonOn = True
    GPIO.output(M1_In1, GPIO.LOW)
    GPIO.output(M1_In2, GPIO.HIGH)
    time.sleep(1)
    GPIO.output(M1_In2, GPIO.LOW)
    buttonOn = False
    sensor=adafruit_bno055.BNO055_I2C(i2c)

"""
Interface gui avec guizero.
Intilisation de l'interface gui avec un titre "Mon app",
un text pour afficher les degrés du capteur (bateau),
deux boutons pour se déplacer vers la droite, ou vers la gauche,
et on le met sur un gridpane.
"""
app = App(title="App", width = 500, height=300, layout="grid")
textMessage = Text(app, text="My app", size =40, font="Times New Roman",color="red", grid=[1,0])
degreeMsg=Text(app,text="0°C",size=30,font="Times New Roman",color="black",grid=[0,1])
upLeftText = PushButton(app,command=testLeft, text="Left", grid=[0,2])
upRightText = PushButton(app, command=testRight, text="Right", grid=[2,2])

"""
Pour le déplacement vers la gauche.
"""
def sens1():
    GPIO.output(M1_In1,GPIO.HIGH)
    GPIO.output(M1_In2,GPIO.LOW)
    
"""
Pour le déplacement vers la gauche.
"""
def sens2():
    GPIO.output(M1_In1,GPIO.LOW)
    GPIO.output(M1_In2,GPIO.HIGH)

"""
Réinitialiser la gauche.
"""
def arret1():
    GPIO.output(M1_In1,GPIO.LOW)
    
"""
Réinitialiser la droite.
"""
def arret2():
    GPIO.output(M1_in2,GPIO.LOW)

"""
Partie Algorithme pour le recalibrage de la position.
Si aucun bouton n'est enclenché, alors le bateau se déplace entre -10° et 10°.
Dès qu'il se déplace en dehors de cette range, alors le bateau se remet à la position 0°,
donc dès qu'il dépasse ce range, il va à droite ou à gauche pour recalibrer le bateau.
Update le message pour afficher le degré du bateau,
et met à jour (refresh) l'interface gui.
"""
while True:
    angle=round(sensor.euler[0],0)
    degreeMsg.value = "{}°C".format(angle)
    print(angle)
            
    if(buttonOn == False):
        if(angle>10 and angle<100 ):
            GPIO.output(goLeft, GPIO.HIGH)
            direction="left"
            sens1()
        elif(angle>300 and angle<350):
            GPIO.output(goRight, GPIO.HIGH)
            direction="right"
            sens2()
        elif(angle >=0 and angle <=10 and direction=="right"):
            GPIO.output(goRight, GPIO.LOW)
            direction=""
            arret2()
        elif(angle <370 and angle >=350 and direction=="left"):
            GPIO.output(goLeft, GPIO.LOW)
            direction=""
            arret1()
        
    
    time.sleep(0.1)
#Pour que ce soit plus précis, on peut rajouter du temps entre chaque cycle.
    #time.sleep(0.5)
    app.update()
    
"""
Affiche l'interface gui.
"""
app.display()
