#include "Game.h"



bool operator!=(const std::vector<Element*>& elems, std::nullptr_t) {
    for (const auto& elem : elems) {
        if (elem != nullptr) {
            return true;
        }
    }
    return false;
}
void Game::saveLevel(){
    this->board->saveLevel();

}

void Game::applyRule(std::string rule, Element elem){
    Element_Is_Stop stopRule;
    Element_Is_Kill killRule;
    Element_Is_Win winRule;
    Element_Is_Push pushRule;
    Element_Is_Sink sinkRule;
    Element_Is_You youRule;
    if (rule == "stop") {
        stopRule.apply(elem,getBoardLoader());
    } else if (rule == "kill") {
        killRule.apply(elem,getBoardLoader());
    } else if (rule == "win") {
        winRule.apply(elem,getBoardLoader());
    } else if (rule == "you") {
        youRule.apply(elem,getBoardLoader());
    } else if (rule == "sink"){
        sinkRule.apply(elem,getBoardLoader());
    } else if (rule == "push"){
        pushRule.apply(elem,getBoardLoader());
    } else {
        throw std::runtime_error("Unknown rule: "+ rule);
    }
}

bool Game::isRule(std::vector<Element*> elemsToCheck){
    for (auto rule :elemsToCheck){
        if (rule->getElementType() == ElementType::RULE){
            return true;
        }
    }
    return false;
}

bool Game::isConnector(std::vector<Element*> elemsToCheck){
    for (auto is :elemsToCheck){
        if (is->getElementType() == ElementType::CONNECTOR){
            return true;
        }
    }
    return false;
}

void Game::whichRule(Element a,Element rule){
    Element_Is_Stop stopRule;
    Element_Is_Kill killRule;
    Element_Is_Win winRule;
    Element_Is_Push pushRule;
    Element_Is_Sink sinkRule;
    Element_Is_You youRule;

    if (rule.getName() == "stop") {
        stopRule.disapply(a,getBoardLoader());
    } else if (rule.getName() == "kill") {
        killRule.disapply(a,getBoardLoader());
    } else if (rule.getName() == "win") {
        winRule.disapply(a,getBoardLoader());
    } else if (rule.getName() == "you") {
        youRule.disapply(a,getBoardLoader());
    } else if (rule.getName() == "sink"){
        sinkRule.disapply(a,getBoardLoader());
    } else if (rule.getName() == "push"){
        pushRule.disapply(a,getBoardLoader());
    } else {

        throw std::runtime_error("Unknown rule: "+ rule.getName());
    }

}

void Game::DisAllRules(Element a){
    Element_Is_Stop stopRule;
    Element_Is_Kill killRule;
    Element_Is_Win winRule;
    Element_Is_Push pushRule;
    Element_Is_Sink sinkRule;
    stopRule.disapply(a,getBoardLoader());
    killRule.disapply(a,getBoardLoader());
    winRule.disapply(a,getBoardLoader());
    pushRule.disapply(a,getBoardLoader());
    sinkRule.disapply(a,getBoardLoader());
    stopRule.disapply(a,getBoardLoader());
}


void Game::disapplyRule(){
    long long unsigned r = 0;
    bool isYou = false;
    Element_Is_You isYouRule;
    std::vector<Element> rule;
    for (auto i = 0; i<getBoardLoader()->getBoard().getBoardRows();i++){
        for (auto j = 0;j< getBoardLoader()->getBoard().getBoardCols(); j++){
            for (auto a : getBoardLoader()->getBoardPtr()->getSquares()[i][j]->getElementsPtr()){
                Position textPos = a->getPosition();
                if (a->getElementType() == ElementType::BLOCK_TEXT){
                    Position downPos (a->getPosition().getRow()+2,a->getPosition().getColumn());
                    if (!checkPositionNotInBoard(downPos)){
                        if (isRule(getBoardLoader()->getBoardPtr()->getSquares()[textPos.getRow()+2][textPos.getColumn()]->getElementsPtr())
                            &&isConnector(getBoardLoader()->getBoardPtr()->getSquares()[textPos.getRow()+1][textPos.getColumn()]->getElementsPtr())){
                            for (auto r : getBoardLoader()->getBoardPtr()->getSquares()[textPos.getRow()+2][textPos.getColumn()]->getElementsPtr()){
                                if (r->getElementType()==ElementType::RULE){
                                    rule.push_back(*r);
                                }
                            }
                        }
                    }
                    Position rightPos (a->getPosition().getRow(),a->getPosition().getColumn()+2);
                    if (!checkPositionNotInBoard(rightPos)){
                        if (isRule(getBoardLoader()->getBoardPtr()->getSquares()[textPos.getRow()][textPos.getColumn()+2]->getElementsPtr())
                            &&isConnector(getBoardLoader()->getBoardPtr()->getSquares()[textPos.getRow()][textPos.getColumn()+1]->getElementsPtr())){
                            for (auto r : getBoardLoader()->getBoardPtr()->getSquares()[textPos.getRow()][textPos.getColumn()+2]->getElementsPtr()){
                                if (r->getElementType()==ElementType::RULE){
                                    rule.push_back(*r);
                                }
                            }
                        }
                    }
                    if (!rule.empty()){
                        if(rule.size()==r+1){
                            whichRule(*a,rule.at(r));
                            r++;
                        }
                        else {
                            DisAllRules(*a);
                        }
                    } else {
                        DisAllRules(*a);
                    }
                }

            }
        }
    }
    int s = 0;
    for (auto checkR : rule){
        if (checkR.getName()=="you"){
            isYou = true;
            if (s > 0){
                changeIsPtr(0,searchYou());
                goto stopChange;
            }
        }
        s++;
    }
stopChange:

    if (!isYou){
        Element e;
        isYouRule.disapply(e, getBoardLoader());
    }

}

int Game::searchYou(){
    for (long long unsigned int i = 0; i < board->getIsPtr().size(); i++) {
        int x = board->getIsPtr().at(i).getPosition().getRow();
        int y = board->getIsPtr()[i].getPosition().getColumn();
        Position bottomPos (x+1,y);
        Position rightPos (x,y+1);
        if (checkPositionNotInBoard(bottomPos)){
            bottomPos= board->getIsPtr().at(i).getPosition();
        }
        if (checkPositionNotInBoard(rightPos)){
            rightPos= board->getIsPtr().at(i).getPosition();
        }
        for (auto u : getBoardLoader()->getBoardPtr()->getSquares()[bottomPos.getRow()][bottomPos.getColumn()]->getElementsPtr()){
            if (u->getName()=="you"){
                return i;
            }
        }
        for (auto u : getBoardLoader()->getBoardPtr()->getSquares()[rightPos.getRow()][rightPos.getColumn()]->getElementsPtr()){
            if (u->getName()=="you"){
                return i;
            }
        }
    }
    return 0;
}

void Game::changeIsPtr(int pos1, int pos2){
    std::vector<Element> newIs = board->getIsPtr();
    std::swap(newIs[pos1], newIs[pos2]);
    board->setIsPtr(newIs);

}


void Game::checkRule(){

    for (long long unsigned int i = 0; i < board->getIsPtr().size(); i++) {
        int x = board->getIsPtr().at(i).getPosition().getRow();
        int y = board->getIsPtr()[i].getPosition().getColumn();
        Position topPos (x-1,y);
        Position bottomPos (x+1,y);
        Position leftPos (x,y-1);
        Position rightPos (x,y+1);
        if (checkPositionNotInBoard(topPos)){
            topPos = board->getIsPtr().at(i).getPosition();
        }
        if (checkPositionNotInBoard(bottomPos)){
            bottomPos= board->getIsPtr().at(i).getPosition();
        }
        if (checkPositionNotInBoard(leftPos)){
            leftPos= board->getIsPtr().at(i).getPosition();
        }
        if (checkPositionNotInBoard(rightPos)){
            rightPos= board->getIsPtr().at(i).getPosition();
        }
        std::vector<Element> top;
        auto temp = board->getBoard().getSquareAtPosition(topPos)->getElements();
        for (auto& elem : temp) {
            top.push_back(elem);
        }
        temp.clear();
        std::vector<Element> bottom;
        temp = board->getBoard().getSquareAtPosition(bottomPos)->getElements();
        for (auto& elem : temp) {
            bottom.push_back(elem);
        }
        temp.clear();
        std::vector<Element> left;
        temp = board->getBoard().getSquareAtPosition(leftPos)->getElements();
        for (auto& elem : temp) {
            left.push_back(elem);
        }
        temp.clear();
        std::vector<Element> right;
        temp = board->getBoard().getSquareAtPosition(rightPos)->getElements();
        for (auto& elem : temp) {
            right.push_back(elem);
        }
        temp.clear();

        bool top_test = false;
        bool bottom_test= false;
        bool left_test= false;
        bool right_test= false;
        for (auto& element_ptr :top){
            if (element_ptr.getElementType()==ElementType::BLOCK_TEXT){
                top_test = true;
                top.clear();
                top.push_back(element_ptr);
            }
        }
        for (auto& element_ptr :bottom){
            if (element_ptr.getElementType()==ElementType::RULE){
                bottom_test = true;
                bottom.clear();
                bottom.push_back((element_ptr));
            }
        }
        for (auto& element_ptr :left){
            if (element_ptr.getElementType()==ElementType::BLOCK_TEXT){
                left_test = true;
                left.clear();
                left.push_back(element_ptr);
            }
        }
        for (auto& element_ptr :right){
            if (element_ptr.getElementType()==ElementType::RULE){
                right_test = true;
                right.clear();
                right.push_back(element_ptr);
            }
        }

        if ((!top.empty() && !bottom.empty()) &&
            (top_test && bottom_test)) {
            this->applyRule(bottom.at(0).getName(),top.at(0));
        } else if ((!left.empty() && !right.empty()) &&
                   (left_test && right_test)) {
            this->applyRule(right.at(0).getName(),left.at(0));
        }
    }
}




BoardLoader* Game::getBoardLoader(){
    return this->board;
}

void Game::setAllPtr(std::vector<Element> test){
    if (test.size()==0){
        goto endSet;
    } else{
        std::string name = test.at(0).getName();
        if (name == "baba"){
            getBoardLoader()->setBabaPtr(test.at(0));
        } else if (name == "flag"){
            getBoardLoader()->setFlagPtr(test.at(0));
        } else if (name == "grass"){
            getBoardLoader()->setGrassPtr(test);
        } else if (name=="lava"){
            getBoardLoader()->setLavaPtr(test);
        } else if (name == "metal"){
            getBoardLoader()->setMetalPtr(test);
        } else if (name == "rock"){
            getBoardLoader()->setRockPtr(test);
        } else if (name == "wall"){
            getBoardLoader()->setWallPtr(test);
        } else if (name == "water"){
            getBoardLoader()->setWaterPtr(test);
        }
    }
endSet:
    test.size();
}

bool Game::checkElemStop(std::vector<Element*> elemsToCheck){
    for (auto stop :elemsToCheck){
        if (stop->getStop()){
            return true;
        }
    }
    return false;
}

bool Game::checkElemPush(std::vector<Element *> elemsToCheck){
    for (auto push :elemsToCheck){
        if (push->getPush()){
            return true;
        }
    }
    return false;
}

bool Game::checkPositionNotInBoard(Position pos){
    return (pos.getRow() < 0 || pos.getRow() > 17 || pos.getColumn() < 0 || pos.getColumn() > 17);
}

bool Game::checkPush(Square* square, Direction dir){
    setZ(getZ()+1);
    Position newPos (square->getPosition()+dir);
    if (checkPositionNotInBoard(newPos)){
        setZ(getZ()-1);
        return false;
    } else {
        std::vector<Element*> check;
        for (auto elem : getBoardLoader()->getBoardPtr()->getSquares()[newPos.getRow()][newPos.getColumn()]->getElementsPtr()){
            if (elem->getPush()){
                check.push_back(elem);
            }
        }
        if (check.empty()){
            setZ(getZ()-1);
            return false;
        }
        else {
            if (checkElemStop(getBoardLoader()->getBoardPtr()->getSquares()[newPos.getRow()][newPos.getColumn()]->getElementsPtr())){
                return false;
            }
            else {
                return checkPush(getBoardLoader()->getBoardPtr()->getSquares().at(newPos.getRow()).at(newPos.getColumn()),dir);
            }
        }
    }
}


void Game::movePushableElems(Square *square, Direction dir){
    int x ;
    int y ;
    std::vector<Element*> dependDir;
    std::vector<Element> updatePosElems;
    for (int j = getZ()-1; j>=0;j--){
        switch(dir){
        case Direction::RIGHT : dependDir = getBoardLoader()->getBoardPtr()->getSquares()[square->getPosition().getRow()][square->getPosition().getColumn()+j]->getElementsPtr();
            break;
        case  Direction::UP : dependDir = getBoardLoader()->getBoardPtr()->getSquares()[square->getPosition().getRow()-j][square->getPosition().getColumn()]->getElementsPtr();
            break;
        case Direction::LEFT : dependDir = getBoardLoader()->getBoardPtr()->getSquares()[square->getPosition().getRow()][square->getPosition().getColumn()-j]->getElementsPtr();
            break;
        case Direction::DOWN : dependDir = getBoardLoader()->getBoardPtr()->getSquares()[square->getPosition().getRow()+j][square->getPosition().getColumn()]->getElementsPtr();
            break;
        default:
        case  Direction::STOP  : break;

        }

        for(auto temp : dependDir){
            Element cpTemp = *temp;
            x =cpTemp.getPosition().getRow();
            y =cpTemp.getPosition().getColumn();
            if (temp->getPush()){
                if (checkPositionNotInBoard(temp->getPosition()+dir)){
                    goto end;
                } else {
                    getBoardLoader()->getBoardPtr()->getSquares()[x][y]->removeElement(temp);
                    cpTemp.setPosition(cpTemp.getPosition()+dir);
                    x =cpTemp.getPosition().getRow();
                    y =cpTemp.getPosition().getColumn();
                    getBoardLoader()->getBoardPtr()->getSquares()[x][y]->addElement(&cpTemp);
                    updateElemPos(cpTemp);
                }
            }

        }
    }

end:
    updatePosElems.size();

}

Position Game::gapInDirection(Square *currentSquare,Direction dir, int gap){
    Position def ;
    gap++;
    Position currentPos = currentSquare->getPosition();
    if (dir==Direction::UP){
        Position nextPosition (currentPos.getRow()-gap,currentPos.getColumn());
        return nextPosition;
    }
    else if (dir ==Direction::DOWN){
        Position nextPosition (currentPos.getRow()+gap,currentPos.getColumn());
        return nextPosition;
    }

    else if (dir == Direction::LEFT){
        Position nextPosition (currentPos.getRow(),currentPos.getColumn()-gap);
        return nextPosition;
    }
    else if (dir == Direction::RIGHT){

        Position nextPosition (currentPos.getRow(),currentPos.getColumn()+gap);
        return nextPosition;
    }
    else {
        return def;
    }
}


void Game::move(Direction &dir){
    std::vector<Element> test;
    Element* temp ;
    Direction newDir = dir;
    int x ;
    int y ;
    for (unsigned i = 0;i<getBoardLoader()->getBoardPtr()->getMovableElements().size();i++){
        Position tempPos {getBoardLoader()->getBoardPtr()->getMovableElements().at(i).getPosition().getRow(),getBoardLoader()->getBoardPtr()->getMovableElements().at(i).getPosition().getColumn()};
        temp = getBoardLoader()->getBoardPtr()->getSquares()[tempPos.getRow()][tempPos.getColumn()]->getElementsPtr().back();
        Element cpTemp = *temp;
        x =cpTemp.getPosition().getRow();
        y =cpTemp.getPosition().getColumn();
        getBoardLoader()->getBoardPtr()->getSquares()[x][y]->removeElement(temp);
        if ((checkPositionNotInBoard(cpTemp.getPosition()+newDir))
            || checkElemStop(getBoardLoader()->getBoardPtr()->getSquares()[(cpTemp.getPosition()+newDir).getRow()][(cpTemp.getPosition()+newDir).getColumn()]->getElementsPtr())){
            cpTemp.setPosition(temp->getPosition());
        } else {
            if (checkPush(getBoardLoader()->getBoardPtr()->getSquares()[(cpTemp.getPosition()).getRow()][(cpTemp.getPosition()).getColumn()], newDir)){
                cpTemp.setPosition(temp->getPosition()); //d'office false
            } else {
                if (getZ()<1){
                    if (checkElemPush(getBoardLoader()->getBoardPtr()->getSquares()[(cpTemp.getPosition()+newDir).getRow()][(cpTemp.getPosition()+newDir).getColumn()]->getElementsPtr())){
                        cpTemp.setPosition(temp->getPosition());
                    } else {
                        cpTemp.setPosition(cpTemp.getPosition()+newDir);
                    }
                    setZ(0);
                } else {
                    if (checkPositionNotInBoard(gapInDirection(getBoardLoader()->getBoardPtr()->getSquares()[x][y],newDir,getZ()))){
                        cpTemp.setPosition(temp->getPosition());
                    }
                    else if (checkElemStop(getBoardLoader()->getBoardPtr()->getSquares()[gapInDirection(getBoardLoader()->getBoardPtr()->getSquares()[x][y],newDir,getZ()).getRow()]
                                                                                        [gapInDirection(getBoardLoader()->getBoardPtr()->getSquares()[x][y],newDir,getZ()).getColumn()]->getElementsPtr())){
                        cpTemp.setPosition(temp->getPosition());
                    } else {
                        movePushableElems(getBoardLoader()->getBoardPtr()->getSquares()[(cpTemp.getPosition()+newDir).getRow()][(cpTemp.getPosition()+newDir).getColumn()],newDir);
                        if (checkElemPush(getBoardLoader()->getBoardPtr()->getSquares()[(cpTemp.getPosition()+newDir).getRow()][(cpTemp.getPosition()+newDir).getColumn()]->getElementsPtr())){
                            cpTemp.setPosition(temp->getPosition());
                        } else {
                            cpTemp.setPosition(cpTemp.getPosition()+newDir);
                        }
                    }

                }
                setZ(0);
            }
        }
        test.push_back(cpTemp);
        x =cpTemp.getPosition().getRow();
        y =cpTemp.getPosition().getColumn();
        getBoardLoader()->getBoardPtr()->getSquares()[x][y]->addElement(&cpTemp);
    }
    getBoardLoader()->getBoardPtr()->updateMovableElements(test);
    setAllPtr(test);

    Notify();
}

void Game::updateIsPos(){
    std::vector<Element> isNew;
    for (int i = 0; i< getBoardLoader()->getBoardPtr()->getBoardRows();i++){
        for (int j = 0; j < getBoardLoader()->getBoardPtr()->getBoardCols();j++){
            for (auto is : getBoardLoader()->getBoardPtr()->getSquares()[i][j]->getElementsPtr())
                if (is->getElementType()==ElementType::CONNECTOR){
                    isNew.push_back(*is);
                }
        }
    }
    getBoardLoader()->setIsPtr(isNew);
}

void Game::updateElemPos(Element elem){
    std::vector<Element> elemNew;
    for (int i = 0; i< getBoardLoader()->getBoardPtr()->getBoardRows();i++){
        for (int j = 0; j < getBoardLoader()->getBoardPtr()->getBoardCols();j++){
            for (auto elemToUpdate : getBoardLoader()->getBoardPtr()->getSquares()[i][j]->getElementsPtr())
                if (elemToUpdate->getName()==elem.getName()){
                    elemNew.push_back(*elemToUpdate);
                }
        }
    }
    setAllPtr(elemNew);

}

bool Game::isRestart(){
    bool check;
    if (getBoardLoader()->isNotPlayable()){
        check = true;
        Notify();
    }
    else {
        check = false;
    }
    return check;
}

void Game::Attach(Observer *observer){
    list_of_observer.push_back(observer);
}

void Game::Detach(Observer *observer){
    auto itr =  std::find (list_of_observer.begin (), list_of_observer.end (), observer);
    if (itr != list_of_observer.end ()){
        list_of_observer.erase (itr);
    }
}

void Game::Notify(){
    for (auto itr = list_of_observer.begin (); itr != list_of_observer.end (); itr++){
        (*itr)->Update (this);
    }
}
