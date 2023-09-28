#include <iostream>
#include <QLabel>
#include <vector>
#include "Mainwindow.h"
#include "ui_mainwindow.h"




MainWindow::MainWindow(int rowBoard, int colBoard, std::vector<std::vector<Square*>>  squaresBoard, QWidget *parent )
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    this->setFixedSize(950, 720);
    ui->tableWidget->setRowCount(18);
    ui->tableWidget->setColumnCount(18);
    ui->tableWidget->horizontalHeader()->setSectionResizeMode(QHeaderView::Stretch);
    ui->tableWidget->verticalHeader()->setSectionResizeMode(QHeaderView::Stretch);
    ui->tableWidget->horizontalHeader()->setVisible(false);
    ui->tableWidget->verticalHeader()->setVisible(false);
    ui->tableWidget->setSelectionMode(QAbstractItemView::NoSelection);
    ui->tableWidget->setFocusPolicy(Qt::NoFocus);
    ui->textEdit->setFocusPolicy(Qt::NoFocus);
    ui->textEdit->setReadOnly(true);

    for (int row = 0; row < ui->tableWidget->rowCount(); ++row) {
        ui->tableWidget->setRowHeight(row, 40);
        for (int column = 0; column < ui->tableWidget->columnCount(); ++column) {
            ui->tableWidget->setColumnWidth(column, 40);
        }
    }

    for (int i = 0; i < rowBoard; ++i) {
        for (int j = 0; j < colBoard; ++j) {
            std::vector<Element*> elements =squaresBoard[i][j]->getElementsPtr();

            for (auto element : elements) {
                QLabel* label = new QLabel();
                QString imagePath = "../../Baba_is_you/gui/Images/" + QString::fromStdString(element->getName()) + ".png";
                QPixmap pixmap(imagePath);
                QPixmap resizedImage = pixmap.scaled(38, 38, Qt::KeepAspectRatio);
                label->setPixmap(resizedImage);
                label->adjustSize();
                ui->tableWidget->setCellWidget(i, j, label);
            }
        }
    }
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::displayBoard(Game *g){
    for (int i = 0; i < g->getBoardLoader()->getBoardPtr()->getBoardRows(); ++i) {
        for (int j = 0; j < g->getBoardLoader()->getBoardPtr()->getBoardCols(); ++j) {
            std::vector<Element*> elements = g->getBoardLoader()->getBoardPtr()->getSquares()[i][j]->getElementsPtr();
            if (elements.empty()){
                QLabel* label = new QLabel();
                QString imagePath = "../../Baba_is_you/gui/Images/Empty.png";
                QPixmap pixmap(imagePath);
                QPixmap resizedImage = pixmap.scaled(38, 38, Qt::KeepAspectRatio);
                label->setPixmap(resizedImage);
                label->adjustSize();
                ui->tableWidget->setCellWidget(i, j, label);
            }
            for (auto element : elements) {
                QLabel* label = new QLabel();
                QString imagePath = "../../Baba_is_you/gui/Images/" + QString::fromStdString(element->getName()) + ".png";
                QPixmap pixmap(imagePath);
                QPixmap resizedImage = pixmap.scaled(38, 38, Qt::KeepAspectRatio);
                label->setPixmap(resizedImage);
                label->adjustSize();
                ui->tableWidget->setCellWidget(i, j, label);
            }
        }
    }
}

void MainWindow::printMessage(std::string message){

    QString qMessage = QString::fromStdString(message);
    ui->textEdit->setText(qMessage);
}

void MainWindow::clearTextEdit(){
    ui->textEdit->clear();
}

void MainWindow::Update(Subject * game){
    Game * g  = dynamic_cast< Game *>(game);
    if (g->getBoardLoader()->getBoardPtr()->getMovableElements().empty()){
        ui->textEdit->setText("There is no element 'you'\n"
                                   "Do you want to restart the level ?\n"
                                   "If yes, press 'R', otherwise press any key and the game will stop\n"
                                   "Then press an arrow\n"
                                   "The action won't happen until you press 'R' followed by an arrow.");
        ui->textEdit->setReadOnly(true);
    } else {
        displayBoard(g);
    }
}

void MainWindow::keyPressEvent(QKeyEvent* event)
{
    int i = event->key();
    emit sendSignalKey(i);
}
