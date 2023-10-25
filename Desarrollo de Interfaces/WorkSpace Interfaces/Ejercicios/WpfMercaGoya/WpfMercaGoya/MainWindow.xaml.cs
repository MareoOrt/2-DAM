using Microsoft.VisualBasic;
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
using System.Windows.Media.Media3D;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace WpfMercaGoya
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private List<Articulo> listArticulos = new List<Articulo>();
        private Ticket ticketAct;
        private int contTickets = 0;
        private double cantidadTotal = 0;

        public MainWindow()
        {
            rellenarListArticulos();
            mostrar_lbArticulos();
            // IniciarTicket += mostrar_lbArticulos();
            InitializeComponent();
        }

        private void rellenarListArticulos()
        {
            Articulo art1 = new Articulo();
            art1.precio = 5.30;
            art1.descripcion = "Artículo uno";

            Articulo art2 = new Articulo();
            art2.precio = 10.00;
            art2.descripcion = "Artículo dos";

            Articulo art3 = new Articulo();
            art3.precio = 15.50;
            art3.descripcion = "Artículo tres y media naranja de valencia";
            
            Articulo art4 = new Articulo();
            art4.precio = 20.00;
            art4.descripcion = "Artículo uno";

            Articulo art5 = new Articulo();
            art5.precio = 25.20;
            art5.descripcion = "Artículo uno";

            listArticulos.Add(art1);
            listArticulos.Add(art2);
            listArticulos.Add(art3);
            listArticulos.Add(art4);
            listArticulos.Add(art5);
        }

        public void mostrar_lbArticulos()
        {
            foreach (var articulo in listArticulos)
            {
                String txt = $"{articulo.descripcion} ({articulo.precio}€)";

                lbArticulos.Items.Add(txt);
            }
        }

        private void botNuevo_Click(object sender, RoutedEventArgs e)
        {
            mostrar_lbArticulos();

            lbTicket.Items.Clear();
            ticketAct = new Ticket();
            contTickets++;

            ticketAct.linea.Add("Gregorio-Market");
            ticketAct.linea.Add("");
            ticketAct.linea.Add("********************************************");

            foreach (var l in ticketAct.linea)
            {
                lbTicket.Items.Add(l);
            }

        }

        private void lbArticulos_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {

            
            Articulo articulo = new Articulo();
            foreach (var art in listArticulos)
            {
                if (sender.Equals(art.descripcion))
                {
                    articulo.descripcion = art.descripcion;
                }
            }

            if (!String.IsNullOrEmpty(articulo.descripcion))
            {
                if (articulo.descripcion.Length > 30)
                {
                    String cadena = "";
                    for (int i = 0; i < 30; i++)
                    {
                        cadena += articulo.descripcion[i];
                    }
                    cadena += Char.ConvertFromUtf32(2026);

                    lbTicket.Items.Add($"{cadena}\t\t{articulo.precio}€");
                    ticketAct.linea.Add($"{cadena}\t\t{articulo.precio}€");
                }
                else
                {
                    lbTicket.Items.Add($"{articulo.descripcion}\t\t{articulo.precio}€");
                    ticketAct.linea.Add($"{articulo.descripcion}\t\t{articulo.precio}€");
                }

                ticketAct.Total += articulo.precio;
            }
        }

        private void botCerrar_Click(object sender, RoutedEventArgs e)
        {
            List<String> pieTicket = new List<String>();

            pieTicket.Add("********************************************");
            pieTicket.Add($"Total={ticketAct.Total} €");

            foreach (var item in pieTicket)
            {
            lbTicket.Items.Add(item);

            }

        }

        private void botCambio_Click(object sender, RoutedEventArgs e)
        {
            List<String> finTicket = new List<String>();
            if (!string.IsNullOrEmpty(tbEntrega.Text))
            {
                // if (Double.TryParse(tbEntrega.Text))
                // {
                // }
                // else
                // {
                //    MessageBox.Show("No introduciste una cantidad valida");
                // }

                double cantIntro = Double.Parse(tbEntrega.Text);

                if(cantIntro > ticketAct.Total)
                {
                    double cambio = ticketAct.Total - cantIntro;
                    finTicket.Add($"Entregado={cantIntro} €");
                    finTicket.Add($"Cambio={cambio} €");
                    cantidadTotal += ticketAct.Total;
                }
                else MessageBox.Show("No introduciste ninguna cantidad de dinero");

            }
            else MessageBox.Show("No introduciste ninguna cantidad de dinero");

            foreach (var item in finTicket)
            {
                lbTicket.Items.Add(item);
            }
        }

        private void botResumen_Click(object sender, RoutedEventArgs e)
        {
            String txt = "";
            if (contTickets > 1)
            {
                txt = $"Se han creado {contTickets} tickets \n";
            }
            else
            {
                txt = $"Se han creado {contTickets} ticket \n";
            }
            txt += $"con un total de {cantidadTotal} €";

            MessageBox.Show(txt);
        }
    }

    public class Ticket
    {
        private List<String> Linea;
        public double Total{ get; set; }
        public List<String> linea
        {
            get { return Linea; }
            set { Linea = value; }
        }
        private event EventHandler NuevaLinea;
        private event EventHandler NuevoTicket;

        /*
        public Ticket(string linea, double total)
        {
            this.linea = linea;
            Total = total;
            this.linea = linea;
        }
        */

        public Ticket()
        {
        }
        

        public void IniciarTicket()
        {

        }

    }

    public class Articulo
    {
        public override string? ToString()
        {
            return descripcion;
        }

        private String Descripcion;
        public double precio { get; set; }
        public String descripcion
        {
            get { return Descripcion; }
            set { Descripcion = value; }
        }

    }
}
