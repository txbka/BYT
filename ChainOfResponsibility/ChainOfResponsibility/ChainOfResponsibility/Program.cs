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

        //static void ValidateRequest(string input)
        //{
        //    foreach (char c in input)
        //    {
        //        if (!availableCharacters.Contains(c))
        //        {
        //            throw new Exception("Equation contains invalid characters");
        //        }
        //    }
        //    Divider(input);
        //}

        //static void Divider(string input)
        //{
        //    Regex regex = new Regex("([0-9]+)([/])([0-9]+)");
        //    var matches = regex.Match(input);
        //    while (matches.Success)
        //    {
        //        Console.WriteLine($"#{matches.Groups[0].Value}/{matches.Groups[3].Value}");
        //        int result = Int32.Parse(matches.Groups[1].Value) / Int32.Parse(matches.Groups[3].Value);
        //        input = input.Replace($"{matches.Groups[1].Value}/{matches.Groups[3].Value}", result.ToString());
        //        matches = regex.Match(input);
        //    }
        //    Multiplier(input);           
        //}

        //static void Multiplier(string input)
        //{
        //    Regex regex = new Regex("([0-9]+)([*])([0-9]+)");
        //    var matches = regex.Match(input);
        //    while(matches.Success)
        //    {
        //        Console.WriteLine($"#{matches.Groups[0].Value}*{matches.Groups[3].Value}");
        //        int result = Int32.Parse(matches.Groups[1].Value) * Int32.Parse(matches.Groups[3].Value);
        //        input = input.Replace($"{matches.Groups[1].Value}*{matches.Groups[3].Value}", result.ToString());
        //        matches = regex.Match(input);
        //    }
        //    Adder(input);
        //}

        //static void Adder(string input)
        //{
        //    Regex regex = new Regex("([0-9]+)([+])([0-9]+)");
        //    var matches = regex.Match(input);
        //    while(matches.Success)
        //    {
        //        Console.WriteLine($"#{matches.Groups[0].Value}+{matches.Groups[3].Value}");
        //        int result = Int32.Parse(matches.Groups[1].Value) + Int32.Parse(matches.Groups[3].Value);
        //        input = input.Replace($"{matches.Groups[1].Value}+{matches.Groups[3].Value}", result.ToString());
        //        matches = regex.Match(input);
        //    }
        //    Substractor(input);
        //}
        
        //static void Substractor(string input)
        //{
        //    Regex regex = new Regex("([0-9]+)(-)([0-9]+)");
        //    var matches = regex.Match(input);
        //    while(matches.Success)
        //    {
        //        Console.WriteLine($"#{matches.Groups[0].Value}-{matches.Groups[3].Value}");
        //        int result = Int32.Parse(matches.Groups[1].Value) - Int32.Parse(matches.Groups[3].Value);
        //        input = input.Replace($"{matches.Groups[1].Value}-{matches.Groups[3].Value}", result.ToString());
        //        matches = regex.Match(input);
        //    }
        //    Printer(input);
        //}

        //static void Printer(string input)
        //{
        //    Console.WriteLine(input);
        //}
    }
}
