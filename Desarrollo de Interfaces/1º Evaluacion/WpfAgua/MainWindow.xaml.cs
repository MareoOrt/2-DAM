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

namespace WpfAgua
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        Deposito deposito = new Deposito();
        public MainWindow()
        {
            InitializeComponent();
            Pruebas();

            deposito.DepositoVacio += Deposito_DepositoVacio;
        }

        private void Deposito_DepositoVacio(object? sender, EventArgs e)
        {
            throw new NotImplementedException();
            MessageBox.Show(ex.Message, "ERROR",
                    MessageBoxButton.OK,
                    MessageBoxImage.Error);
        }

        public void Pruebas()
        {
            deposito = new Deposito();
            try
            {
                deposito.Cantidad = 10;
            }
            catch (Exception ex)
            {
                //throw new Exception("ERROR!!" +  ex.Message
                MessageBox.Show(ex.Message, "ERROR",
                    MessageBoxButton.OK,
                    MessageBoxImage.Error);
            }
        }

        private void botPulsar_Click(object sender, RoutedEventArgs e)
        {
            deposito.Cantidad --;
            lblCantidad.Content = deposito.Cantidad;

        }
    }
}
