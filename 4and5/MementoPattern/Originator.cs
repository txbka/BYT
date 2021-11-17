using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MementoPattern
{
    class Originator
    {
        public string State { get; set; }

        public Originator(string state)
        {
            this.State = state;
        }

        public void Restore(Memento memento)
        {
            State = memento.State;
        }

        public Memento Save()
        {
            return new Memento(State);
        }
    }
}
