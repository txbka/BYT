using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MementoPattern
{
    class CareTaker
    {
        private List<Memento> _backup = new List<Memento>();
        private Originator _originator;

        public CareTaker(Originator originator)
        {
            this._originator = originator;
        }
        public void Backup()
        {
            _backup.Add(_originator.Save());
        }

        public void Undo()
        {
            if(_backup != null && _backup.Count != 0)
            {
                var tmpBackup = _backup.Last();
                _backup.Remove(tmpBackup);
                _originator.Restore(tmpBackup);
            }
        }
    }
}
