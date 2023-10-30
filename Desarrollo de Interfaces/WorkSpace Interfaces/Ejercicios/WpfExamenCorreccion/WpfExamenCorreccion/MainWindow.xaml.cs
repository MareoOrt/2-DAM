using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection.Metadata.Ecma335;
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

namespace WpfExamenCorreccion
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        private void mouseRight(Object sender, System.Windows.Input.)
        {

        }

        void CargarArticulos()
        {

        }

        private void botNuevoTicket(Object sender, RoutedEventArgs e)
        {

        }

        void ticket_nevoTicket(Object sender, RoutedEventArgs e)
        {

        }

        private void lbArticulos_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            // Articulo articulo = lbArticulos.SelectedItem;
            Articulo articulo = new Articulo();

            String descripcion = articulo.Descripcion.PadRight(30)[..30]
            + (articulo.Descripcion.Length > 30 ? 'u/2602' : "");



        }

    }

    public class Articulo
    {
        private String descripcion;

        public String Descripcion
        {
            get { return descripcion; }
            set { descripcion = value; }
        }

        public double Precio { get; set; }

        public override string? ToString()
        {
            return $"{this.Descripcion} ({this.Precio:C})";
        }
    }

    public class Ticket
    {
        public event EventHandler NuevaLinea;
        public event EventHandler NuevOTciket;

        public Ticket() { }

        public String[] Lista { get; set; }

    }
}
