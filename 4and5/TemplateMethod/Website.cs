using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TemplateMethod
{
    public abstract class Website
    {
        public void RunWebsite()
        {
            StartWebsite();
            NewVisitiors();
            Maintenance();
            UnderAttack();
            UpdateWebsite();
            RestartWebsite();
        }
        public abstract void StartWebsite();
        public abstract void NewVisitiors();
        public abstract void Maintenance();
        public abstract void UpdateWebsite();
        public abstract void UnderAttack();
        public abstract void RestartWebsite();
    }
}
