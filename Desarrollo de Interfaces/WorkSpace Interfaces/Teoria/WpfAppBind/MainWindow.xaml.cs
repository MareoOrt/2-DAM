using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace WpfAppBind
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        List<Persona> Personas;
        private int indice;

        public int Indice
        {
            get { return indice; }
            set
            {
                if (value >= 0 && value < Personas.Count)
                {
                    indice = value;
                    spPersona.DataContext = Personas[indice];
                }
            }
        }

        public MainWindow()
        {
            InitializeComponent();
        }

        private void BotonCargar_Click(object sender, RoutedEventArgs e)
        {
            Personas = Datos.GetPersonas();
            spPersona.DataContext = Personas.First();
            Indice = 0;
        }

        private void BotonPrimero_Click(object sender, RoutedEventArgs e)
        {
            spPersona.DataContext = Personas.First(); Indice = 0;
        }

        private void BotonUltimo_Click(object sender, RoutedEventArgs e)
        {
            spPersona.DataContext = Personas.Last(); Indice = Personas.Count - 1;
        }

        private void BotonSiguiente_Click(object sender, RoutedEventArgs e)
        {
            Indice++;
        }

        private void BotonAnterior_Click(object sender, RoutedEventArgs e)
        {
            Indice--;
        }
    }
}
