#include "Subject.h"

void Subject::Attach(Observer *observer)
{
    observers_.insert(observer);
}

void Subject::Detach(Observer *observer)
{
    observers_.erase(observer);
}

void Subject::Notify()
{
    for (Observer * observer : observers_)
    {
        observer->Update(this);
    }
}
