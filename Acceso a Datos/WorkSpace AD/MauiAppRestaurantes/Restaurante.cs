using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MauiAppRestaurantes
{
    internal class Restaurante
    {
        public string Titulo { get; set; }
        public string Nombre { get; set; }
        public string Lema { get; set; }
        public string Direccion { get; set; }
        public string Localidad { get; set; }
        public string Provincia { get; set; }
        public string Foto { get; set; }
        public Color ColorBase { get; set; }
    }
}
