using System;

namespace TemplateMethod
{
    class Program
    {
        static void Main(string[] args)
        {
            SmallWebsite smallWebsite = new SmallWebsite();
            smallWebsite.RunWebsite();
            Console.WriteLine("");
            MediumUpdatableWebsite mediumUpdatableWebsite = new MediumUpdatableWebsite();
            mediumUpdatableWebsite.RunWebsite();
            Console.WriteLine("");
            BigUnderAttackWebsite bigUnderAttackWebsite = new BigUnderAttackWebsite();
            bigUnderAttackWebsite.RunWebsite();
        }
    }
}
