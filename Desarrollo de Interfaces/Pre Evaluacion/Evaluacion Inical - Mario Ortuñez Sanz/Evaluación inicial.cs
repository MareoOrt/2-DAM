using System.Runtime.Serialization;

namespace EvaluaciónInicial
{
    internal class Evaluacion
    {
        static void Main(string[] args)
        {
            DateTime hoy = DateTime.Now;

            int hora = hoy.Hour;

            if (hora < 14)
            {
                Console.WriteLine("Buenos dias");
            }
            else
            {
                Console.WriteLine("Buenas tardes");
            }

            Console.WriteLine("Son las " + hoy);

            string frase = Console.ReadLine();

            string fraseinversa = "";

            int numeroLetras = 0;

            foreach (char letra in frase)
            {
                fraseinversa = letra + fraseinversa;
                numeroLetras++;
            }
            if (numeroLetras != 0)
            {

                Console.WriteLine("Tu frase fue: " + frase);
                Console.WriteLine("Invertida es: " + fraseinversa);

                Console.WriteLine("La frase tenia " + numeroLetras);
            }
            else
            {
                Console.WriteLine("No escribistes nada");
            }
        }

    }
}
