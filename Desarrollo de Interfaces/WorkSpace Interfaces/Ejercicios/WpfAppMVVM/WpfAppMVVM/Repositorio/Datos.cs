using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using WpfAppMVVM.Models;

namespace WpfAppMVVM.Repositorio
{
    internal class Datos
    {
        public static ObservableCollection<Persona> Personas { get; set; } = new();

        static String[] Nombres = { "Juan", "Ana", "Alicia", "Antonio", "Luis", "Alberto" };
        static String[] Apellidos = { "Pérez", "Gómez", "López", "Ramírez", "Gutiérrez" };

        public static ObservableCollection<Persona> GetPersonas()
        {
            int n = Personas.Count;
            for (int i = n; i <= n + 4; i++)
            {
                Personas.Add(new Persona()
                {
                    Id = i,
                    Nombre = Nombres[new Random().Next(Nombres.Length)],
                    Apellidos = $"{Apellidos[new Random().Next(Apellidos.Length)]} {Apellidos[new Random().Next(Apellidos.Length)]}",
                    Telefono = (new Random().Next(623456980, 679876540).ToString())
                });
            }
            return Personas;
        }
    }

}
