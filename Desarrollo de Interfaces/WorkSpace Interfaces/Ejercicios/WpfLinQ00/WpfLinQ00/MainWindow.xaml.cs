using System;
using System.Collections.Generic;
using System.Linq;
using System.Windows;

namespace WpfLinQ00
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
            // Ejemplo1();
            // Ejemplo2();
            // Ejemplo3();
            // Ejemplo4();
            // Ejemplo5();
        }

        private void Ejemplo1()
        {
            List<int> enteros = new List<int> { 20, 56, 84, 13, 6, 2 };
            var resultado = from n in enteros
                            where n > 20
                            orderby n descending
                            select n;

            int cont = 0;
            foreach (var item in resultado)
            {
                tb1.Text += $"{++cont:000}...{item}\n";
                // tb1.Text += (++cont).ToString("000")+"~~~~"+ item +'\t';
            }
        }

        private void Ejemplo2()
        {
            List<int> enteros = new List<int> { 20, 56, 84, 13, 6, 2 };
            var resultado = from n in enteros
                            where n % 2 == 0
                            orderby n descending
                            select $"{n:C0}";
            foreach (String item in resultado)
            {
                tb1.Text += $"{item}\n";
            }
        }

        private void Ejemplo3()
        {
            List<int> enteros = new List<int> { 20, 56, 84, 13, 6, 2 };
            var resultado = from n in enteros
                            where n % 2 == 0
                            orderby n descending
                            select new
                            {
                                numero = n,
                                letrita = (char)(n + 64)

                            };
            foreach (var item in resultado)
            {
                tb1.Text += $"\t{item.letrita}\t{item.numero}\n";
            }

        }

        private void Ejemplo4()
        {
            List<Persona> personas = new List<Persona>()
            {
                new Persona{
                    Dni="111111",
                    Nombre="Juancito",
                    Apellidos="Pérez Pí",
                    Telefono="983548798"},
                new Persona{Nombre="Alicia", Dni="222222",
                    Apellidos="Gómez Narro", Telefono="693548721"},
                new Persona{Nombre="Pedro", Dni="333333",
                    Apellidos="Del Alto Sombrero", Telefono="653259874"},
            };

            IEnumerable<Persona> resultado = from p in personas
                                             select p;

            foreach (Persona persona in resultado)
            {
                tb1.Text += $"{persona.NombreCompleto.PadRight(40, '.')} " +
                    $"({persona.Telefono})\n";

                // tb1.Text += $"{persona.NombreCompleto, -40} {persona.Telefono}\n";
            }

            foreach (Persona persona in resultado)
            {
                var resultado2 = personas.Select(p =>
                    new
                    {
                        nom = persona.NombreCompleto,
                        tel = persona.Telefono
                    });

                foreach (var item in resultado2)
                {

                    tb1.Text += $"{item.nom,40} - {item.tel}\n";
                }
            }
        }

        private void Ejemplo5()
        {
            List<Persona> personas;
            List<Vehiculo> vehiculos;

            CargarColecciones(out personas, out vehiculos);

            IEnumerable<VistaPropietarios> resultado =
                from p in personas
                join v in vehiculos on p.Dni equals v.DniPropietario
                select new VistaPropietarios
                {
                    Nombre = p.NombreCompleto,
                    Matricula = v.Matricula,
                    Modelo = v.Modelo
                };

            String g = "";
            foreach (VistaPropietarios item in resultado)
            {
                if(item.Nombre != g)
                {
                    tb1.Text += $"{item.Nombre.PadRight(50, '_')}\n";
                    g = item.Nombre;
                }
                tb1.Text += $"\t\t{item.Matricula} \t {item.Modelo}\n";
            }

        }

        public static void CargarColecciones(out List<Persona> personas, out List<Vehiculo> vehiculos)
        {
            personas = new List<Persona>()
            {
                new Persona{
                    Dni="111111",
                    Nombre="Juancito",
                    Apellidos="Pérez Pí",
                    Telefono="983548798"},
                new Persona{Nombre="Alicia", Dni="222222",
                    Apellidos="Gómez Narro", Telefono="983548798"},
                new Persona{Nombre="Pedro", Dni="333333",
                    Apellidos="Del Alto Sombrero", Telefono="983548798"},
            };
            vehiculos = new List<Vehiculo>()
            {
                new Vehiculo{Matricula="AAAAAA",DniPropietario="111111", Modelo="Renault Clio 1.3 100cv",Color="negro"},
                new Vehiculo{Matricula="BBBBBB",DniPropietario="222222", Modelo="Peugeout 2008 1.3 100cv",Color="blanco"},
                new Vehiculo{Matricula="CCCCCC",DniPropietario="111111", Modelo="Seat Toledo 1.3 150cv",Color="blanco"},
                new Vehiculo{Matricula="DDDDDD",DniPropietario="333333", Modelo="Audi A7 3.0 250cv",Color="negro"},
                new Vehiculo{Matricula="EEEEEE",DniPropietario="222222", Modelo="Peugeout 2008 1.4 150cv",Color="VERDE"},
                new Vehiculo{Matricula="FFFFFF",DniPropietario="222222", Modelo="Peugeout 2008 1.5 200cv",Color="AZUL"},

            };
        }
    }

    public class Vehiculo
    {
        public String Matricula { get; set; }
        public String DniPropietario { get; set; }
        public String Modelo { get; set; }
        public String Color { get; set; }
    }

    public class Persona
    {
        public String Dni { get; set; }
        public String Apellidos { get; set; }
        public String Telefono { get; set; }

        public String Nombre;

        public String NombreCompleto
        {
            get { return $"{this.Nombre} {this.Apellidos}"; }
            set { Nombre = value; }
        }

    }

    public class VistaPropietarios
    {
    public String Nombre { get; set; }
    public String Matricula { get; set; }
    public String Modelo { get; set; }
}
}
