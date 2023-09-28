#include "Mainwindow.h"
#include "ControllerGUI.h"
#include <QApplication>



int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    ControllerGUI c {0};
    c.play();
    return a.exec();
}
