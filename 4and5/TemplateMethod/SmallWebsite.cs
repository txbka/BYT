using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace TemplateMethod
{
    class SmallWebsite : Website
    {
        public override void Maintenance()
        {
        }

        public override void NewVisitiors()
        {
            Random randPeople = new Random();
            Random randInterval = new Random();
            for(int i = 0; i < 5; i++)
            {
                int randPeopleRes = randPeople.Next(1, 10);
                Console.WriteLine(string.Format("{0} new visitor{1}", randPeopleRes, randPeopleRes == 1 ? "" : "s"));
                Thread.Sleep(randInterval.Next(1, 3) * 1000);
            }
        }

        public override void RestartWebsite()
        {
        }

        public override void StartWebsite()
        {
            Console.WriteLine("Small website just started");
        }

        public override void UnderAttack()
        {
        }

        public override void UpdateWebsite()
        {
        }
    }
}
