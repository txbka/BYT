using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace TemplateMethod
{
    class BigUnderAttackWebsite : Website
    {
        public override void Maintenance()
        {
            Console.WriteLine("Big under attack website is currently under attack");
        }

        public override void NewVisitiors()
        {
            Random randPeople = new Random();
            Random randInterval = new Random();
            for (int i = 0; i < 25; i++)
            {
                int randPeopleRes = randPeople.Next(1, 500);
                Console.WriteLine(string.Format("{0} new visitor{1}", randPeopleRes, randPeopleRes == 1 ? "" : "s"));
                Thread.Sleep(randInterval.Next(1, 3) * 250);
            }
        }

        public override void RestartWebsite()
        {
            Console.WriteLine("Restarting big under attack website");
        }

        public override void StartWebsite()
        {
            Console.WriteLine("Big under attack website just started");
        }

        public override void UnderAttack()
        {
            Console.WriteLine("Implementing reCAPTCHA in big under attack website");
        }

        public override void UpdateWebsite()
        {
            Console.WriteLine("Uploading update to big under attack website");
        }
    }
}
