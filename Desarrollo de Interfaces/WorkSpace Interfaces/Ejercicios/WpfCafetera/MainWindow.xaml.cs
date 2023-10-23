using System;
using System.Collections;
using System.Collections.Generic;
using System.Drawing;
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
using Color = System.Drawing.Color;

namespace WpfCafetera
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    
    public partial class MainWindow : Window
    {
        private Deposito deposito;
        private double[] precios = { 3.0, 2.0, 1.0, 0.5, 0.3, 0.2, 0.1 };
        private String[] productos = { "café solo", "café cortado", "chocolate", "té" };
        private double dinero = 0.0;

        public MainWindow()
        {
            InitializeComponent();
            deposito = new Deposito(precios);
            deposito.Iniciar();
            MostrarProductos();
            MostrarPrecios();
        }

        private void ActualizarVuelta()
        {
            tbVuelta.Text = "Vuelta: " + deposito.Devolver().ToString();
        }

        private void MostrarProductos()
        {
            foreach (string producto in productos)
            {
                Button button = new Button
                {
                    Content = producto
                };
                button.Click += Producto_Click_Producto;
                spProductos.Children.Add(button);
            }
        }

        private void MostrarPrecios()
        {
            foreach (double precio in precios)
            {
                Button button = new Button
                {
                    Content = "" + precio
                };
                button.Click += Moneda_Click;
                spMonedas.Children.Add(button);
            }
        }

        private void Producto_Click_Producto(object sender, RoutedEventArgs e)
        {

            if (deposito.Dispensar(deposito.Total))
            {
                tbDispensador.Text = "Producto dispensado";
                ActualizarVuelta();
            }
            else
            {
                tbDispensador.Text = "Saldo insuficiente";
            }
        }

        private void Moneda_Click(object sender, RoutedEventArgs e)
        {
            Button boton = (Button)sender;
            double dinero = Convert.ToDouble(boton.Content);
            if (deposito.EsMonedaValida(dinero))
            {
                tbDispensador.Text = "Dinero insertado";
            }
            else{
                tbDispensador.Text = "Moneda no válida";
            }

        }
    }
}

