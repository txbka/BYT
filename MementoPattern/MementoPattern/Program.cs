using System;

namespace MementoPattern
{
    class Program
    {
        static void Main(string[] args)
        {
            Originator originator = new Originator("Initial state");
            CareTaker careTaker = new CareTaker(originator);

            careTaker.Backup();
            originator.State = "New state";
            Console.WriteLine($"State has changed to: {originator.State}");
            careTaker.Backup();
            originator.State = "New state2";
            Console.WriteLine($"State has changed to: {originator.State}");

            Console.WriteLine("Rolling back changes");
            careTaker.Undo();
            Console.WriteLine($"Current state is: {originator.State}");
        }
    }
}
