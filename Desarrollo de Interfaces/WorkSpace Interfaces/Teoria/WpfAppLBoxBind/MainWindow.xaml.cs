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

namespace WpfAppLBoxBind
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
        private void Button_Click(object sender, RoutedEventArgs e)
        {
            Datos.AgregarPersona("Cristiano Romualdo", "12458978", "caricaturas/4.png");
        }

        private void botonSeleccionar_Click(object sender, RoutedEventArgs e)
        {
            Window detalle = new WindowDetalle();
            detalle.DataContext = lbPersonas.SelectedItem;
            detalle.Show();
        }
        protected override void OnActivated(EventArgs e)
        {
            base.OnActivated(e);
            Datos.RecuperarDatos();
        }
    }
}

