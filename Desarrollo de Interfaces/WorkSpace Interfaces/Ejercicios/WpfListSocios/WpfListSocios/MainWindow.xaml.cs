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

namespace WpfListSocios
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {

        // Coleccion de socios
        private List<Socio> coleccionSocios = new List<Socio>();

        // Socio Seleccionado
        private Socio socioS;

        public MainWindow()
        {
            inicializarList();
            completarLB();
            InitializeComponent();
        }

        #region MetodosVarios

        // Metodo para añadir elementos de forma manual a la lista
        public void inicializarList()
        {
            coleccionSocios.Add(new Socio(
                "Juán",
                "Pérez Pí",
                "11111111A",
                DateTime.Parse("01/01/2001"),
                "11111111111"));

            coleccionSocios.Add(new Socio(
                "Alicia",
                "Gómez Tal",
                "222222222B",
                DateTime.Parse("02/02/2002"),
                "222222222"));

            coleccionSocios.Add(new Socio(
                "Pedro",
                "Rodrigo Cual",
                "333333333C",
                DateTime.Parse("03/03/2003"),
                "333333333"));

        }

        // Metodo para añadir elementos al listBox de socios
        public void completarLB()
        {
            foreach (var socio in coleccionSocios)
            {
                String socioAdd = socio.NombreCompleto;
                ListBoxItem lbi = new ListBoxItem();    
                lbi.Content = socioAdd;
                lb1.Items.Add(lbi);
            }
        }

        // Metodo para comporbar si los elementos fueron introducidos
        public bool comprobarCompleto()
        {
            if (string.IsNullOrEmpty(tbApellidos.Text) &&
                string.IsNullOrEmpty(tbNombre.Text) &&
                string.IsNullOrEmpty(tbDni.Text) &&
                string.IsNullOrEmpty(tbTelefono.Text))
            {
                return false;
            }else return true;
        }

        #endregion

        #region ButtonClicks
        // Botón mostrar
        private void Button_Click_1(object sender, RoutedEventArgs e)
        {
            completarLB();
        }

        // Botón buscar
        private void Button_Click_2(object sender, RoutedEventArgs e)
        {
            String nombre = tbNombre.Text;

            foreach (var socio in coleccionSocios)
            {
                if (socio.nombre.Equals(nombre))
                {
                    lb1.Items.Add(socio.NombreCompleto);
                }
            }

        }

        // Boton Actualizar
        private void Button_Click_3(object sender, RoutedEventArgs e)
        {
            if (comprobarCompleto())
            {
                String nombre = tbNombre.Text;
                String apellidos = tbApellidos.Text;
                String dni = tbDni.Text;
                String telefono = tbTelefono.Text;
                // DateTime fechaN = dpFechaNacimiento.Value;



            }
        }
        #endregion

        private void lb1_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            
        }
    }
}
