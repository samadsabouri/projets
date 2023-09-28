#ifndef BOARDLOADER_H
#define BOARDLOADER_H
#include <vector>
#include <filesystem>
#include <fstream>
#include "Board.h"


/**
 * @brief The BoardLoader class is responsible for loading the game board and movable elements for a specific level from a text file.
 */
class BoardLoader{

private:

    int levelNumber;
    Board board;
    std::vector<Element> movableElementLoader;

    Element baba_ptr;
    Element baba_text_ptr;
    std::vector<Element> bone_ptr;
    Element flag_ptr;
    Element flag_text_ptr;
    std::vector<Element> grass_ptr;
    Element grass_text_ptr;
    std::vector<Element> is_ptr;
    Element kill_ptr;
    std::vector<Element> lava_ptr;
    Element lava_text_ptr;
    std::vector<Element> metal_ptr;
    Element metal_text_ptr;
    Element push_ptr;
    std::vector<Element> rock_ptr;
    Element rock_text_ptr;
    Element sink_ptr;
    Element stop_ptr;
    std::vector<Element> wall_ptr;
    Element wall_text_ptr;
    std::vector<Element> water_ptr;
    Element water_text_ptr;
    Element win_ptr;
    Element you_ptr;

    //methodes privées
    /**
     * @brief elementPtr is a private helper method that sets the pointer to an element based on the element name.
     * @param elem A pointer to the element to set.
     */
    void elementPtr(Element* elem);


    /**
     * @brief switchDirection is a private helper method that converts a direction integer to the corresponding direction enum value.
     * @param dir A reference to the integer value representing the direction.
     * @return The corresponding direction enum value.
     */
    Direction switchDirection(int& dir);


    /**
     * @brief switchElementType is a private helper method that converts an element name to the corresponding element type enum value.
     * @param name A reference to the string value representing the element name.
     * @return The corresponding element type enum value.
     */
    ElementType switchElementType(std::string& name);

    /**
     * @brief Determines if an element with the given name is movable
     * @param name The name of the element
     * @return True if the element is movable, false otherwise
     */
    bool isMovable(std::string& name);

    /**
     * @brief Reads a file containing the level data for the given level number
     * @param levelNumber The number of the level to read
     * @return A vector of strings containing the lines of the file
     */
    std::vector<std::string> fileReader(int& levelNumber);

    /**
     * @brief Extracts the number of rows and columns in the board from the file data
     * @param fileLines The lines of the file containing the level data
     * @param boardRows A reference to an integer to store the number of rows
     * @param boardCols A reference to an integer to store the number of columns
     */
    void extractBoardRowsCols(std::vector<std::string>& fileLines, int &boardRows, int& boardCols);

    /**
     * @brief Parses the squares from the file data and stores them in a vector
     * @param fileLines The lines of the file containing the level data
     * @param fileSize The number of lines in the file
     * @param squares A reference to a vector to store the parsed squares
     */
    void getReadedSquares( std::vector<std::string>& fileLines,int& fileSize, std::vector<Square>& squares);

    /**
     * @brief Updates the squares vector with empty squares to fill the board
     * @param boardRows The number of rows in the board
     * @param boardCols The number of columns in the board
     * @param squares A reference to a vector containing the parsed squares
     * @param squareSize The size of each square in pixels
     * @param squares2 A reference to a 2D vector to store the updated squares
     */
    void updateWithEmptySquares(int & boardRows, int& boardCols, std::vector<Square>& squares, int& squareSize, std::vector<std::vector<Square>>& squares2);

    /**
     * @brief Returns the symbol for the opposite element type of the given name
     * @param name The name of the element
     * @return The symbol for the opposite element type
     */
    std::string switchSymbol(std::string name);

    /**
     * @brief Creates the 2D vector of squares representing the game board
     * @param boardRows The number of rows in the board
     * @param boardCols The number of columns in the board
     * @param fileSquares A reference to a vector containing the parsed squares
     * @param boardSquares A reference to a 2D vector to store the board squares
     */
    void createBoardSquares(int & boardRows, int& boardCols, std::vector<Square>& fileSquares, std::vector<std::vector<Square>>& boardSquares);

public:
    /**
     * @brief Constructs a BoardLoader object for the given level number
     * @param levelNumber The number of the level to load
     */
    BoardLoader(int levelNumber);

    /**
     * @brief Saves the current level.
     */
    void saveLevel();
    /**
     * @brief Sets the level number for the BoardLoader object
     */
    inline void setLevel(int level){
        this->levelNumber  = level;
    }

    /**
     * @brief Returns the current level number
     * @return The current level number
     */
    inline int getLevelNumber()const{
        return levelNumber;
    }

    /**
     * @brief Returns the Board object constructed from the level data
     * @return The Board object
     */
    inline Board getBoard()const{
        return board;
    }

    /**
     * @brief Returns a pointer to the Board object
     * @return  Pointer to the Board object
     */
    inline Board* getBoardPtr(){
        return &board;
    }

    /**
     * @brief Returns a vector of movable elements in the level data
     * @return Vector of movable elements
     */
    inline std::vector<Element> getMovableElemLoader(){
        return movableElementLoader;
    }

    /**
     * @brief Checks if the level is playable
     * @return True if the level is not playable, false otherwise
     */
    bool isNotPlayable();



    // Getters:

    /**
     * @brief Gets a pointer to the Baba element
     * @return Pointer to the Baba element
     */
    Element getBabaPtr() const;

    /**
     * @brief Gets a pointer to the text representation of the Baba element
     * @return Pointer to the text representation of the Baba element
     */
    Element getBabaTextPtr() const;

    /**
     * @brief  Gets a vector of pointers to the Bone element
     * @return Vector of pointers to the Bone elements
     */
    std::vector<Element> getBonePtr() const;

    /**
     * @brief Gets a pointer to the Flag element
     * @return Pointer to the Flag element
     */
    Element getFlagPtr() const ;

    /**
     * @brief Gets a pointer to the text representation of the Flag element
     * @return Pointer to the text representation of the Flag element
     */
    Element getFlagTextPtr() const;

    /**
     * @brief Gets a vector of pointers to the Grass elements
     * @return Vector of pointers to the Grass elements
     */
    std::vector<Element> getGrassPtr() const;

    /**
     * @brief Gets a vector of pointers to the Is elements
     * @return Vector of pointers to the Is elements
     */
    std::vector<Element> getIsPtr() const ;

    /**
     * @brief Gets a pointer to the Kill element
     * @return Pointer to the Kill element
     */
    Element getKillPtr() const;

    /**
     * @brief Gets a vector of pointers to the Lava elements
     * @return Vector of pointers to the Lava elements
     */
    std::vector<Element> getLavaPtr() const ;

    /**
     * @brief Gets a vector of pointers to the Metal elements
     * @return Vector of pointers to the Metal elements
     */
    std::vector<Element> getMetalPtr() const ;

    /**
     * @brief Gets a pointer to the text representation of the Metal element
     * @return Pointer to the text representation of the Metal element
     */
    Element getMetalTextPtr() const ;

    /**
     * @brief Gets a pointer to the Push element
     * @return Pointer to the Push element
     */
    Element getPushPtr() const ;

    /**
     * @brief Gets a vector of pointers to the Rock elements
     * @return Vector of pointers to the Rock elements
     */
    std::vector<Element> getRockPtr() const ;

    /**
     * @brief Gets a pointer to the text representation of the Rock element
     * @return Pointer to the text representation of the Rock element
     */
    Element getRockTextPtr() const ;

    /**
     * @brief Gets a pointer to the Sink element
     * @return Pointer to the Sink element
     */
    Element getSinkPtr() const ;

    /**
     * @brief Gets a pointer to the Stop element
     * @return Pointer to the Stop element
     */
    Element getStopPtr() const ;

    /**
     * @brief Gets a vector of pointers to the Wall elements
     * @return Vector of pointers to the Wall elements
     */
    std::vector<Element> getWallPtr() const ;

    /**
     * @brief Gets a vector of pointers to the Water elements
     * @return Vector of pointers to the Water elements
     */
    std::vector<Element> getWaterPtr() const ;

    /**
     * @brief Get a pointer to the "Water" Element object
     * @return A constant pointer to the "Water" Element object
     */
    Element getWaterTextPtr() const ;

    /**
     * @brief Get a pointer to the "Win" Element object
     * @return A constant pointer to the "Win" Element object
     */
    Element getWinPtr() const ;

    /**
     * @brief Get a pointer to the "You" Element object
     * @return A constant pointer to the "You" Element object
     */
    Element getYouPtr() const ;


    //Setters:

    /**
     * @brief Set the "Baba" Element object
     * @param baba The new "Baba" Element object to set
     */
    void setBabaPtr(Element baba);

    /**
     * @brief  Set the "Baba is" Element object
     * @param babaText The new "Baba is" Element object to set
     */
    void setBabaTextPtr(Element babaText) ;

    /**
     * @brief Set the "Bone" Element objects
     * @param bone the new vector of "Bone" Element objects to set
     */
    void setBonePtr(std::vector<Element> bone);

    /**
     * @brief Set the "Flag" Element object
     * @param flag The new "Flag" Element object to set
     */
    void setFlagPtr(Element flag) ;

    /**
     * @brief Set the "Flag is" Element object
     * @param flagText The new "Flag is" Element object to set
     */
    void setFlagTextPtr(Element flagText) ;

    /**
     * @brief Set the "Grass" Element object
     * @param grass The new vector of "Grass" Element objects to set
     */
    void setGrassPtr(std::vector<Element> grass);

    /**
     * @brief Set the "Grass is" Element object
     * @param grassText The new "Grass is" Element object to set
     */
    void setGrassTextPtr(Element grassText) ;

    /**
     * @brief Set the "Is" Element objects
     * @param is The new vector of "Is" Element objects to set
     */
    void setIsPtr(std::vector<Element> is) ;

    /**
     * @brief Set the "Kill" Element object
     * @param  kill The new "Kill" Element object to set
     */
    void setKillPtr(Element kill);

    /**
     * @brief Set the "Lava" Element objects
     * @param lava The new vector of "Lava" Element objects to set
     */
    void setLavaPtr(std::vector<Element> lava);

    /**
     * @brief Set the "Lava is" Element object
     * @param lavaText The new "Lava is" Element object to set
     */
    void setLavaTextPtr(Element lavaText) ;

    /**
     * @brief Set the "Metal" Element objects
     * @param metal The new vector of "Metal" Element objects to set
     */
    void setMetalPtr(std::vector<Element> metal) ;

    /**
     * @brief Set the "Metal is" Element object
     * @param metalText The new "Metal is" Element object to set
     */
    void setMetalTextPtr(Element metalText) ;

    /**
     * @brief Set the "Push" Element object
     * @param push The new "Push" Element object to set
     */
    void setPushPtr(Element push) ;

    /**
     * @brief Set the "Rock" Element objects
     * @param rock The new vector of "Rock" Element objects to set
     */
    void setRockPtr(std::vector<Element> rock);

    /**
     * @brief Set the "Rock is" Element object
     * @param rockText The new "Rock is" Element object to set
     */
    void setRockTextPtr(Element rockText);

    /**
     * @brief Set the "Sink" Element object
     * @param sink The new "Sink" Element object to set
     */
    void setSinkPtr(Element sink) ;

    /**
     * @brief Set the pointer to the Element representing the "Stop" object.
     * @param  stop Pointer to the Element representing the "Stop" object.
     */
    void setStopPtr(Element stop) ;

    /**
     * @brief Set the pointer to the vector of Elements representing the "Wall" objects.
     * @param wall Vector of Elements representing the "Wall" objects.
     */
    void setWallPtr(std::vector<Element> wall) ;

    /**
     * @brief Set the pointer to the Element representing the text of the "Wall" object.
     * @param wallText Pointer to the Element representing the text of the "Wall" object.
     */
    void setWallTextPtr(Element wallText) ;

    /**
     * @brief Set the pointer to the vector of Elements representing the "Water" objects.
     * @param water Vector of Elements representing the "Water" objects.
     */
    void setWaterPtr(std::vector<Element> water);

    /**
     * @brief Set the pointer to the Element representing the text of the "Water" object.
     * @param waterText Pointer to the Element representing the text of the "Water" object.
     */
    void setWaterTextPtr(Element waterText) ;

    /**
     * @brief Set the pointer to the Element representing the "Win" object.
     * @param win Pointer to the Element representing the "Win" object.
     */
    void setWinPtr(Element win) ;

    /**
     * @brief Set the pointer to the Element representing the "You" object.
     * @param you Pointer to the Element representing the "You" object.
     */
    void setYouPtr(Element you) ;
};

#endif // BOARDLOADER_H
