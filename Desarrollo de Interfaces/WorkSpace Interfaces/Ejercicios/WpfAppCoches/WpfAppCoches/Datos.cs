using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WpfAppCoches
{
    internal class Datos
    {
        public static ObservableCollection<Coche> Coches { get; set; } = new();

        public static void RecuperarDatos()
        {
            if (Coches.Count == 0)
            {
                Coches.Add(new Coche { Matricula = "1234BGK", Fabricante = "Peugeot", Modelo = "207", Precio = 3000, URLFoto = "/pg207.jpg" });
            }
        }

        public void AddCoche(String matricula, String fabricante, String modelo, double precio, String url)
        {
            Coches.Add(new Coche { Matricula = matricula, Fabricante = fabricante, Modelo = modelo, Precio = precio, URLFoto = url });
        }
    }
}
