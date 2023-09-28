#include"BoardLoader.h"
#include <map>


bool sortByPosition(const Square& a, const Square& b) {
    return a.getPosition() < b.getPosition();
}

bool existSquare(std::vector<Square>& squares ,Position& pos){
    for(const auto &square: squares){
        if(square.getPosition() == pos){
            return true;
        }
    }
    return false;

}

BoardLoader::BoardLoader(int  levelNumber): levelNumber{levelNumber}{
    int boardCols{};
    int boardRows{};
    std::vector<Square> fileSquares;
    std::vector<std::string> fileLines = fileReader(levelNumber);
    std::vector<std::vector<Square>> boardSquares;
    extractBoardRowsCols(fileLines, boardRows,  boardCols);
    int fileSize = static_cast<int>(fileLines.size());
    getReadedSquares( fileLines, fileSize,fileSquares);
    int squareSize = fileSquares.size();
    updateWithEmptySquares( boardRows,  boardCols, fileSquares, squareSize, boardSquares);
    this->board = {boardRows, boardCols, boardSquares,movableElementLoader};

};

void BoardLoader::createBoardSquares(int & boardRows, int& boardCols, std::vector<Square>& fileSquares, std::vector<std::vector<Square>>& boardSquares){

    std::vector<Square> tempCol;
    for(int k{}; k<boardRows; ++k){
        for(int j{}; j<boardCols; ++j){
            Square temp{fileSquares.front()};
            fileSquares.erase(fileSquares.begin());
            tempCol.push_back(temp);
        }
        boardSquares.push_back(tempCol);
        tempCol.clear();
    }

}


void BoardLoader::updateWithEmptySquares(int & boardRows, int& boardCols, std::vector<Square>& fileSquares, int& squareSize, std::vector<std::vector<Square>>& boardSquares){

    for(int i{0};i<boardRows;i++){
        for(int j{0};j<boardCols;j++){
            Position pos {i,j};
            if(!existSquare(fileSquares,pos)){
                Square emptySquare {pos};
                fileSquares.push_back(emptySquare);

            }
        }
    }

    squareSize = fileSquares.size();


    std::sort(fileSquares.begin(), fileSquares.end(), sortByPosition);
    createBoardSquares( boardRows, boardCols,  fileSquares, boardSquares);


}

std::string BoardLoader::switchSymbol(std::string name){
    if (name == "text_baba") {
        return "baba ";
    } else if (name == "baba") {
        return "(#_#)";
    } else if (name == "is") {
        return "_is_ ";
    } else if (name == "text_flag") {
        return "flag ";
    } else if (name == "flag") {
        return "  F  ";
    } else if (name == "text_lava") {
        return "lava ";
    } else if (name == "lava") {
        return "~~~~~";
    } else if (name == "text_wall") {
        return "wall ";
    } else if (name == "wall") {
        return "|||||";
    }else if(name == "text_rock"){
        return "rock ";
    }else if (name == "rock") {
        return "  O  ";
    } else if (name == "metal") {
        return "  X  ";
    } else if (name == "stop") {
        return "stop ";
    } else if (name == "push") {
        return "push ";
    } else if (name == "kill") {
        return "kill ";
    } else if (name == "win") {
        return "win  ";
    } else if (name == "you") {
        return "you  ";
    }else if(name == "water"){
        return"WWWWW";
    }else if(name == "text_water"){
        return "water";
    }else if(name == "grass"){
        return "^^^^^";
    }else if(name == "sink"){
        return "sink ";
    } else if(name == "text_grass"){
        return "grass";
    }else {
        return "     ";
    }
}

void BoardLoader::elementPtr(Element* elem){
    Element temp = *elem;
    if (elem->getName()=="baba"){
        baba_ptr =temp;
    } else if (elem->getName()=="text_baba"){
        baba_text_ptr = temp;
    } else if (elem->getName()=="bone"){
        bone_ptr.push_back(temp);
    } else if (elem->getName()=="flag"){
        flag_ptr = temp;
    } else if (elem->getName()=="text_flag"){
        flag_text_ptr = temp;
    } else if (elem->getName()=="grass"){
        grass_ptr.push_back(temp);
    } else if (elem->getName()=="text_grass"){
        grass_text_ptr = temp;
    } else if (elem->getName()=="is"){
        is_ptr.push_back(temp);
    } else if (elem->getName()=="kill"){
        kill_ptr = temp;
    } else if (elem->getName()=="lava"){
        lava_ptr.push_back(temp);
    } else if (elem->getName()=="text_lava"){
        lava_text_ptr = temp;
    } else if (elem->getName()=="metal"){
        metal_ptr.push_back(temp);
    } else if (elem->getName()=="text_metal"){
        metal_text_ptr = temp;
    } else if (elem->getName()=="push"){
        push_ptr = temp;
    } else if (elem->getName()=="rock"){
        rock_ptr.push_back(temp);
    } else if (elem->getName()=="text_rock"){
        rock_text_ptr = temp;
    } else if (elem->getName()=="sink"){
        sink_ptr = temp;
    } else if (elem->getName()=="stop"){
        stop_ptr = temp;
    } else if (elem->getName()=="wall"){
        wall_ptr.push_back(temp);
    } else if (elem->getName()=="text_wall"){
        wall_text_ptr = temp;
    } else if (elem->getName()=="water"){
        water_ptr.push_back(temp);
    } else if (elem->getName()=="text_water"){
        water_text_ptr = temp;
    } else if (elem->getName()=="win"){
        win_ptr = temp;
    } else if (elem->getName()=="you"){
        you_ptr = temp;
    }
}


void BoardLoader::getReadedSquares(std::vector<std::string>& fileLines, int& fileSize, std::vector<Square>& fileSquares) {
    int colElement{};
    int rowElement{};
    Position pos{colElement, rowElement};
    int dirElement{};
    std::string nameElement{""};
    std::map<Position, std::vector<Element>> elementsMap;

    for(int i{0}; i < fileSize; i++) {
        std::istringstream iss(fileLines[i]);
        iss >> nameElement >> colElement >> rowElement  >> dirElement;
        pos = {rowElement, colElement};
        bool movable{isMovable(nameElement)};

        Element element{pos, switchDirection(dirElement), switchElementType(nameElement),
                        nameElement, movable, switchSymbol(nameElement)};

        if (element.getElementType() == ElementType::BLOCK_TEXT || element.getElementType() == ElementType::CONNECTOR ||
            element.getElementType() == ElementType::RULE) {
            element.setPush(true);
        } else {
            element.setPush(false);
        }

        elementPtr(&element);

        if (!movable) {
            element.setMoveble(true);
            movableElementLoader.push_back(element);
        }

        elementsMap[pos].push_back(element);
    }

    for (auto& pair : elementsMap) {
        Square square{pair.second, pair.first};
        fileSquares.push_back(square);
    }
}

void BoardLoader::extractBoardRowsCols(std::vector<std::string>& fileLines, int &boardRows, int& boardCols){
    std::istringstream iss(fileLines[0]);
    iss >> boardRows >>boardCols ;
    fileLines.erase(fileLines.begin());
}
void BoardLoader::saveLevel(){
    std::ofstream fichier("../../Baba_is_you/metier/levels/level_-1.txt");

    if (fichier.is_open()) {
        fichier << this->getBoard().getBoardRows() << " " << this->getBoard().getBoardCols() << std::endl;

        for(int i {}; i< this->getBoard().getBoardRows();i++ ){
            for(int j {}; j< this->getBoard().getBoardCols();j++){
                if(!this->getBoard().getSquares()[i][j]->getElements().empty()){
                    for(unsigned f{}; f < this->getBoard().getSquares()[i][j]->getElements().size(); f++){
                        std::string name {this->getBoard().getSquares()[i][j]->getElements()[f].getName()};
                        int col {this->getBoard().getSquares()[i][j]->getElements()[f].getPosition().getColumn()};
                        int row {this->getBoard().getSquares()[i][j]->getElements()[f].getPosition().getRow()};
                        int dir {static_cast<int>(this->getBoard().getSquares()[i][j]->getElements()[f].getDirection())};
                        fichier << name << " "<< col << " " << row << " " << dir << std::endl;
                    }

                }
            }
        }
        fichier.close();
    } else {
        throw std::invalid_argument("Erreur : Impossible d'ouvrir le fichier.");
    }
}

std::vector<std::string> BoardLoader::fileReader(int& levelNumber){
    std::string levelNumberString {std::to_string(levelNumber)};

    std::filesystem::path filePath("../../Baba_is_you/metier/levels/level_"+levelNumberString+".txt");
    std::ifstream inputFile(filePath);

    std::vector<std::string> fileLines;
    if (inputFile.is_open()) {
        std::string line;
        while (std::getline(inputFile, line)) {
            fileLines.push_back(line);
        }

        inputFile.close();

    }else {
        throw std::invalid_argument("Erreur : Impossible d'ouvrir le fichier.");
    }
    return fileLines;

}

ElementType BoardLoader::switchElementType(std::string& name){


    if (name.find("_") != std::string::npos) {
        return ElementType::BLOCK_TEXT;
    } else if (name == "push" || name == "stop" || name == "win" || name == "you" || name == "sink" || name == "kill") {
        return ElementType::RULE;
    } else if (name == "is") {
        return ElementType::CONNECTOR;
    } else {
        return ElementType::BLOCK;
    }
}
Direction BoardLoader::switchDirection(int& dir) {
    switch(dir) {
    case 0:
        return  Direction::RIGHT;
    case 1:
        return  Direction::UP;
    case 2:
        return  Direction::LEFT;
    default:
        return  Direction::DOWN;

    }

}

bool BoardLoader::isMovable(std::string& name){
    if(name.compare("baba")){
        return true;
    }
    return false;
}


bool BoardLoader::isNotPlayable(){
    return (this->getBoard().getMovableElements().empty());
}

Element BoardLoader::getBabaPtr() const { return baba_ptr; }
Element BoardLoader::getBabaTextPtr() const { return baba_text_ptr; }
std::vector<Element> BoardLoader::getBonePtr() const { return bone_ptr; }
Element BoardLoader::getFlagPtr() const { return flag_ptr; }
Element BoardLoader::getFlagTextPtr() const { return flag_text_ptr; }
std::vector<Element> BoardLoader::getGrassPtr() const { return grass_ptr; }
std::vector<Element> BoardLoader::getIsPtr() const { return is_ptr; }
Element BoardLoader::getKillPtr() const { return kill_ptr; }
std::vector<Element> BoardLoader::getLavaPtr() const { return lava_ptr; }
std::vector<Element> BoardLoader::getMetalPtr() const { return metal_ptr; }
Element BoardLoader::getMetalTextPtr() const { return metal_text_ptr; }
Element BoardLoader::getPushPtr() const { return push_ptr; }
std::vector<Element> BoardLoader::getRockPtr() const { return rock_ptr; }
Element BoardLoader::getRockTextPtr() const { return rock_text_ptr; }
Element BoardLoader::getSinkPtr() const { return sink_ptr; }
Element BoardLoader::getStopPtr() const { return stop_ptr; }
std::vector<Element> BoardLoader::getWallPtr() const { return wall_ptr; }
std::vector<Element> BoardLoader::getWaterPtr() const { return water_ptr; }
Element BoardLoader::getWaterTextPtr() const { return water_text_ptr; }
Element BoardLoader::getWinPtr() const { return win_ptr; }
Element BoardLoader::getYouPtr() const { return you_ptr; }

void BoardLoader::setBabaPtr(Element baba) { baba_ptr = baba; }
void BoardLoader::setBabaTextPtr(Element babaText) { baba_text_ptr = babaText; }
void BoardLoader::setBonePtr(std::vector<Element> bone) { bone_ptr = bone; }
void BoardLoader::setFlagPtr(Element flag) { flag_ptr = flag; }
void BoardLoader::setFlagTextPtr(Element flagText) { flag_text_ptr = flagText; }
void BoardLoader::setGrassPtr(std::vector<Element> grass) { grass_ptr = grass; }
void BoardLoader::setGrassTextPtr(Element grassText) { grass_text_ptr = grassText; }
void BoardLoader::setIsPtr(std::vector<Element> is) { is_ptr = is; }
void BoardLoader::setKillPtr(Element kill) { kill_ptr = kill; }
void BoardLoader::setLavaPtr(std::vector<Element> lava) { lava_ptr = lava; }
void BoardLoader::setLavaTextPtr(Element lavaText) { lava_text_ptr = lavaText; }
void BoardLoader::setMetalPtr(std::vector<Element> metal) { metal_ptr = metal; }
void BoardLoader::setMetalTextPtr(Element metalText) { metal_text_ptr = metalText; }
void BoardLoader::setPushPtr(Element push) { push_ptr = push; }
void BoardLoader::setRockPtr(std::vector<Element> rock) { rock_ptr = rock; }
void BoardLoader::setRockTextPtr(Element rockText) { rock_text_ptr = rockText; }
void BoardLoader::setSinkPtr(Element sink) { sink_ptr = sink; }
void BoardLoader::setStopPtr(Element stop) { stop_ptr = stop; }
void BoardLoader::setWallPtr(std::vector<Element> wall) { wall_ptr = wall; }
void BoardLoader::setWallTextPtr(Element wallText) { wall_text_ptr = wallText; }
void BoardLoader::setWaterPtr(std::vector<Element> water) { water_ptr = water; }
void BoardLoader::setWaterTextPtr(Element waterText) { water_text_ptr = waterText; }
void BoardLoader::setWinPtr(Element win) { win_ptr = win; }
void BoardLoader::setYouPtr(Element you) { you_ptr = you; }
