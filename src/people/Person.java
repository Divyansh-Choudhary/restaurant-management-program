package people;
abstract class person
{
    int id;
    String name;
    person(int id, String name)
    {
        this.id=id;
        this.name=name;
    }
    abstract void display();
}
