#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QString>
#include <string>
#include <QVBoxLayout>
#include <QWidget>
#include <QTableWidget>
#include <QApplication>
#include <QTableWidgetItem>
#include <QMainWindow>
#include <QKeyEvent>
#include "../metier/Game.h"
#include "../metier/Observer.h"



QT_BEGIN_NAMESPACE
namespace Ui { class MainWindow; }
QT_END_NAMESPACE

class MainWindow : public QMainWindow, public Observer

{
    Q_OBJECT

public:
    /**
     * @brief Constructor of Mainwindow.
     */
    MainWindow(int rowBoard =0, int colBoard=0, std::vector<std::vector<Square*>>  squaresBoard ={},QWidget *parent = nullptr);

    /**
      * @brief Displays the game board.
      * @param game The game instance.
      */
    void displayBoard(Game* g);

    /**
      * @brief Prints a message to the console.
      * @param The message to be printed.
      */
    void printMessage(std::string message);

    /**
     * @brief clearTextEdit to clear the textEdit ui.
     */
    void clearTextEdit ();

    /**
     * @brief The update method for the Observer pattern.
     * @param subject The subject instance.
     */

    virtual void Update(Subject *) override;

    /**
     * @brief The destructor of the class.
     */
    ~MainWindow();
protected:
    /**
     * @brief keyPressEvent to catch an event and do things depending on that.
     * @param event the event to catch.
     */
    void keyPressEvent(QKeyEvent* event) override;


signals:
    /**
     * @brief sendSignalKey to send a signal in manual.
     * @param event the event in the signat to send.
     */
    void sendSignalKey(int event);

    /**
     * @brief keyPressed to see if a key is pressed.
     * @param event tje event to catch if any key is pressed.
     */
    void keyPressed(int event);


private:
    Ui::MainWindow *ui;
};

#endif // MAINWINDOW_H
