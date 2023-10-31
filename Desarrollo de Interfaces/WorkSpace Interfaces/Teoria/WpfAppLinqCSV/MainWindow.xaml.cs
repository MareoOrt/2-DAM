using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;

namespace WpfAppLinqCSV
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();

            foreach (var item in LeerCSV())
            {
                lbEmpleados.Items.Add($"{item.Apellidos}, {item.Nombre} ({item.FechaNacimiento:d})");
            }

            lvResultados.ItemsSource = LeerCSV().OrderBy(emp => emp.Edad);

            CollectionView view = (CollectionView)CollectionViewSource.GetDefaultView(lvResultados.ItemsSource);
            PropertyGroupDescription groupDescription = new PropertyGroupDescription("Departamento");
            view.GroupDescriptions.Add(groupDescription);
        }

        private List<Empleado> LeerCSV()
        {
            /*
            return (File.ReadAllLines("Empleados.csv")
                         .Select(x => x.Split(','))
                         .Select(x =>
                              new Empleado
                              {
                                  Nombre = x[0],
                                  Apellidos = x[1],
                                  FechaNacimiento = DateTime.Parse(x[2]),
                                  Departamento = x[3]
                              })).ToList<Empleado>();
            */
            return (from e in File.ReadAllLines("Empleados.csv")
                    let campos = e.Split(',')
                    select new Empleado
                    {
                        Nombre = campos[0],
                        Apellidos = campos[1],
                        FechaNacimiento = DateTime.Parse(campos[2]),
                        Departamento = campos[3]
                    }).ToList<Empleado>());
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            Panel.SetZIndex(lbEmpleados, 1);
            Panel.SetZIndex(lvResultados, 0);
        }

        private void Button_Click_1(object sender, RoutedEventArgs e)
        {
            Panel.SetZIndex(lvResultados, 1);
            Panel.SetZIndex(lbEmpleados, 0);
        }
    }

    internal class Empleado
    {
        public string? Nombre { get; set; }
        public string Apellidos { get; set; }
        public DateTime FechaNacimiento { get; set; }
        public string Departamento { get; set; }
        public int Edad
        {
            get
            {
                int edad = DateTime.Now.Year - FechaNacimiento.Year;
                return (FechaNacimiento > DateTime.Now.AddYears(-edad)) ? --edad : edad;
            }
        }
    }
}
