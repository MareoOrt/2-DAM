using System;
using System.Collections.Generic;
using System.Configuration;
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

namespace wpfAdivinaNumero
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        int contadorBotones = 0;
        String numeroEnClave = "";

        public MainWindow()
        {
            InitializeComponent();
        }

        private void Button_Click(object? sender , RoutedEventArgs e)
        {
            if (!string.IsNullOrEmpty(numeroEnClave))
            {
                try
                {
                    Button boton = (Button)sender;
                    string numero = boton.Content.ToString();

                    TbPropuesto.Text += numero;
                    contadorBotones++;

                    boton.Visibility = Visibility.Hidden;

                }
                catch (Exception ex)
                {
                    MessageBox.Show(ex.Message, "Metodo Button Click",
                                    MessageBoxButton.OK,
                                    MessageBoxImage.Error);
                    throw ex;
                }

                if (contadorBotones >= 4)
                {
                    PanelDigitos.Visibility = Visibility.Hidden;
                }
            }
            else
            {
                MessageBox.Show("Warning", "Para iniciar dale al boton Otro número",
                                    MessageBoxButton.OK);
            }
        }

        private void BotComprobar_Click(object sender, RoutedEventArgs e)
        {
            if (!string.IsNullOrEmpty(numeroEnClave))
            {
                String numerosUs = TbPropuesto.Text;
                String[] numeroUs = numerosUs.Split("");

                String[] numeroEC = numeroEnClave.Split("");

                int contadorAciertos = 0;
                int contadorErrores = 0;

                foreach (String numero in numeroEC)
                {
                    foreach (String n in numeroUs)
                    {
                        if (n == numero)
                        {
                            contadorAciertos++;
                        }
                        else
                        {
                            contadorErrores++;
                        }
                    }
                }

                String resultado = numerosUs + "\t" + contadorAciertos + "|" + contadorErrores;

                LbHistorial.Items.Add(resultado);

                BotCancelar_Click(sender, e);

            }
            else
            {
                MessageBox.Show("Warning", "Para iniciar dale al boton Otro número",
                                    MessageBoxButton.OK);
            }
        }

        private void BotCancelar_Click(object sender, RoutedEventArgs e)
        {
            contadorBotones = 0;
            PanelDigitos.Visibility = Visibility.Visible;
            TbPropuesto.Text = "";
            bot0.Visibility = Visibility.Visible;
            bot1.Visibility = Visibility.Visible;
            bot2.Visibility = Visibility.Visible;
            bot3.Visibility = Visibility.Visible;
            bot4.Visibility = Visibility.Visible;
            bot5.Visibility = Visibility.Visible;
            bot6.Visibility = Visibility.Visible;
            bot7.Visibility = Visibility.Visible;
            bot8.Visibility = Visibility.Visible;
            bot9.Visibility = Visibility.Visible;

        }

        private void BotOtro_Click(object sender, RoutedEventArgs e)
        {
            BotCancelar_Click(sender, e);
            LbHistorial.Items.Clear();

            Random random = new Random();
            for (int i = 0; i < 4; i++)
            {
                numeroEnClave += random.Next(0, 9);
            }
        }
    }
}
