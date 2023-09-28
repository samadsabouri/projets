#ifndef OBSERVER_H
#define OBSERVER_H
#include <string>

class Subject;

class Observer
{

public:

    virtual ~Observer(){}

    /*!
    * \brief Pure virtual method that each concrete observer must
    * implement: it is this method that the observed subject calls when
    * a notification.
    *
    * \param subject the subject of observation that notifies a change.
    * \see Subject::notifyObservers().
    */
    virtual void Update(Subject * subject) = 0;


protected:

    Observer () = default;



};

#endif // OBSERVER_H


