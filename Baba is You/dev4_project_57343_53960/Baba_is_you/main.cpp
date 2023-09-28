#include <iostream>
#include <filesystem>
#include <fstream>
#include <vector>
#include "metier/BoardLoader.h"
#include <conio.h>
#include "metier/Game.h"
#include "console/View.h"
using namespace std;



int main() {

    BoardLoader boardLoader{4};
    Game g (&boardLoader);
    View v;
    g.checkRule();
    while(true){
        v.displayBoard(&g);

        g.move();
        g.checkRule();
    }


    return 0;


}
