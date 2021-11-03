using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace ChainOfResponsibility
{
    public abstract class Handler
    {
        public abstract void Calculate(string input);
        public abstract void Pass(string input);
    }

    public class Reader : Handler
    {
        public Handler next;
        public override void Calculate(string input)
        {
            Console.WriteLine("***Integer calculator***");
            string inputS;
            if (string.IsNullOrEmpty(input))
            {
                string available = "";
                for (int i = 0; i < Program.availableCharacters.Length; i++)
                {
                    available += i == Program.availableCharacters.Length - 1 ? Program.availableCharacters[i] : $"{Program.availableCharacters[i]}, ";
                }
                Console.WriteLine($"Please provide your equation (available characters: [{available}])!");
                inputS = Console.ReadLine();
            }
            else
            {
                Console.WriteLine(input);
                inputS = input;
            }           
            
            foreach (char c in inputS)
            {
                if (!Program.availableCharacters.Contains(c))
                {
                    throw new Exception("Equation contains invalid characters");
                }
            }
            Pass(inputS);
        }

        public override void Pass(string input)
        {
            if (next != null)
            {
                next.Calculate(input);
            }
            else
            {
                Console.Write($"=\n={input}");
            }
        }
    }

    public class Adder : Handler
    {
        public Handler next;
        public override void Calculate(string input)
        {
            Regex regex = new Regex("([0-9]+)([+])([0-9]+)");
            var matches = regex.Match(input);
            while (matches.Success)
            {
                int result = Int32.Parse(matches.Groups[1].Value) + Int32.Parse(matches.Groups[3].Value);
                input = input.Replace($"{matches.Groups[1].Value}+{matches.Groups[3].Value}", result.ToString());
                matches = regex.Match(input);
            }
            Pass(input);
        }

        public override void Pass(string input)
        {
            if (next != null)
            {
                next.Calculate(input);
            }
            else
            {
                Console.Write($"=\n={input}");
            }
        }
    }
    public class Substractor : Handler
    {
        public Handler next;
        public override void Calculate(string input)
        {
            Regex regex = new Regex("([0-9]+)(-)([0-9]+)");
            var matches = regex.Match(input);
            while (matches.Success)
            {
                int result = Int32.Parse(matches.Groups[1].Value) - Int32.Parse(matches.Groups[3].Value);
                input = input.Replace($"{matches.Groups[1].Value}-{matches.Groups[3].Value}", result.ToString());
                matches = regex.Match(input);
            }
            Pass(input);
        }

        public override void Pass(string input)
        {
            if (next != null)
            {
                next.Calculate(input);
            }
            else
            {
                Console.Write($"=\n={input}");
            }
        }
    }
    public class Divider : Handler
    {
        public Handler next;

        public override void Calculate(string input)
        {
            Regex regex = new Regex("([0-9]+)([/])([0-9]+)");
            var matches = regex.Match(input);
            while (matches.Success)
            {
                int result = Int32.Parse(matches.Groups[1].Value) / Int32.Parse(matches.Groups[3].Value);
                input = input.Replace($"{matches.Groups[1].Value}/{matches.Groups[3].Value}", result.ToString());
                matches = regex.Match(input);
            }
            Pass(input);
        }

        public override void Pass(string input)
        {
            if (next != null)
            {
                next.Calculate(input);
            }
            else
            {
                Console.Write($"=\n={input}");
            }
        }
    }
    public class Multiplier : Handler
    {
        public Handler next;

        public override void Calculate(string input)
        {
            Regex regex = new Regex("([0-9]+)([*])([0-9]+)");
            var matches = regex.Match(input);
            while (matches.Success)
            {
                int result = Int32.Parse(matches.Groups[1].Value) * Int32.Parse(matches.Groups[3].Value);
                input = input.Replace($"{matches.Groups[1].Value}*{matches.Groups[3].Value}", result.ToString());
                matches = regex.Match(input);
            }
            Pass(input);
        }

        public override void Pass(string input)
        {
            if (next != null)
            {
                next.Calculate(input);
            }
            else
            {
                Console.Write($"=\n={input}");
            }
        }
    }
    public class Printer : Handler
    {
        public Handler next;
        public override void Calculate(string input)
        {
            Pass(input);
        }

        public override void Pass(string input)
        {
            if(next != null)
            {
                next.Calculate(input);
            }
            else
            {
                Console.Write($"={input}");
            }
        }
    }
}
