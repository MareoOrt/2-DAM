using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WpfListSocios
{
    internal class Socio
    {

        #region Propiedades
        // Nombre, Apellidos, Dni, FechaNacimiento, Telefono
        public String nombre { get; set; }

        public String apellidos{ get; set; }

        public String dni { get; set; }

        public DateTime? fechaNaciemiento { get; set; }

        public String telefono { get; set; }

        // NombreCompleto
        public String nombrecompleto;

        public String NombreCompleto
        {
            get { return $"{apellidos}, {nombre}"; }
        }
        #endregion

        // Constructor
        public Socio(string nombre, string apellidos, string dni, DateTime fechaNaciemiento, string telefono)
        {
            this.nombre = nombre;
            this.apellidos = apellidos;
            this.dni = dni;
            this.fechaNaciemiento = fechaNaciemiento;
            this.telefono = telefono;
        }

        // toString
        public override string? ToString()
        {
            return this.nombre + this.apellidos;
        }

    }

}
