using System;
using System.Linq;
using System.Text.RegularExpressions;

namespace ChainOfResponsibility
{
    class Program
    {
        public static char[] availableCharacters = new char[] { '+', '-', '/', '*', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        static void Main(string[] args)
        {
            Printer printer = new Printer() { next = null };
            Adder adder = new Adder() { next = printer };
            Substractor substractor = new Substractor() { next = adder };
            Multiplier multiplier = new Multiplier() { next = substractor };
            Divider divider = new Divider() { next = multiplier };
            Reader reader = new Reader() { next = divider };
            reader.Calculate(args.Length==0?"":args[1]);
        }

    }
}
