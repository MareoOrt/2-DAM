using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WpfAppBind
{
    internal class Datos
    {
        public static List<Persona> GetPersonas()
        {
            List<Persona> personas = new List<Persona>();
            personas.Add(new Persona() { Id = 0, Nombre = "Juan", Apellidos = "Pérez Pi", Telefono = "654321987" });
            personas.Add(new Persona() { Id = 1, Nombre = "Alicia", Apellidos = "Pérez Pi", Telefono = "654321987" });
            personas.Add(new Persona() { Id = 2, Nombre = "Ana", Apellidos = "Pérez Pi", Telefono = "654321987" });
            personas.Add(new Persona() { Id = 3, Nombre = "Antonio", Apellidos = "Pérez Pi", Telefono = "654321987" });
            return personas;
        }
    }
}
