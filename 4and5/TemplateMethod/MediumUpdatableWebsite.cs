using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace TemplateMethod
{
    class MediumUpdatableWebsite : Website
    {
        public override void Maintenance()
        {
            Console.WriteLine("Medium updatable website is currently under maintenance");
        }

        public override void NewVisitiors()
        {
            Random randPeople = new Random();
            Random randInterval = new Random();
            for (int i = 0; i < 10; i++)
            {
                int randPeopleRes = randPeople.Next(1, 100);
                Console.WriteLine(string.Format("{0} new visitor{1}", randPeopleRes, randPeopleRes == 1 ? "" : "s"));
                Thread.Sleep(randInterval.Next(1, 3) * 750);
            }
        }

        public override void RestartWebsite()
        {
            Console.WriteLine("Restarting medium updatable website");
        }

        public override void StartWebsite()
        {
            Console.WriteLine("Medium updatable website just started");
        }

        public override void UnderAttack()
        {
        }

        public override void UpdateWebsite()
        {
            Console.WriteLine("Uploading update to medium updatable website");
        }
    }
}
