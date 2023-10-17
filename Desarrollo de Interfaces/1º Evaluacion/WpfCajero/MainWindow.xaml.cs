using System;
using System.Collections.Generic;
using System.Windows;
using System.Windows.Controls;

namespace WpfCajero
{
    public partial class MainWindow : Window
    {
        private Dictionary<int, int> billetesDisponibles = new Dictionary<int, int>
        {
            { 200, 10 },
            { 100, 20 },
            { 50, 30 },
            { 20, 40 },
            { 10, 50 }
        };

        private string cadenaPin;
        private int contadorPIN = 0;
        private bool sesionIniciada = false;
        private List<int> billetesRetirados = new List<int>();

        public MainWindow()
        {
            InitializeComponent();
            ActualizarListaBilletes();
        }

        private void Digitos_Click(object sender, RoutedEventArgs e)
        {
            Button button = (Button)sender;
            if (contadorPIN < 4 && !sesionIniciada)
            {
                cadenaPin += button.Content.ToString();
                textBlockDisplay.Text += "*";
                contadorPIN++;
            }
            else if (sesionIniciada)
            {
                textBlockDisplay.Text += button.Content.ToString();
            }
        }

        private void botonOk_Click(object sender, RoutedEventArgs e)
        {
            if (!string.IsNullOrEmpty(cadenaPin))
            {
                if (comprobarPin())
                {
                    textBlockDisplay.Text = "¡Bienvenido!\nSeleccione la cantidad a retirar.";
                    sesionIniciada = true;
                }
                else
                {
                    textBlockDisplay.Text = "PIN incorrecto. Intente nuevamente.";
                }
            }
            else
            {
                textBlockDisplay.Text = "PIN incorrecto. No se registró ningún carácter.";
            }
        }

        private bool comprobarPin()
        {
            // Agrega tu lógica de autenticación aquí
            // Puedes usar una base de datos o algún otro método seguro
            return cadenaPin.Equals("1234"); // Por ejemplo, PIN 1234
        }

        private void botonBorrar_Click(object sender, RoutedEventArgs e)
        {
            if (sesionIniciada)
            {
                textBlockDisplay.Text = "Seleccione la cantidad a retirar.";
            }
            else
            {
                textBlockDisplay.Text = "Introduzca su PIN: ";
                cadenaPin = "";
                contadorPIN = 0;
            }
        }

        private void botonRecoger_Click(object sender, RoutedEventArgs e)
        {
            if (sesionIniciada)
            {
                if (billetesRetirados.Count > 0)
                {
                    RealizarRetiro();
                    ActualizarListaBilletes();
                    sesionIniciada = false;
                    textBlockDisplay.Text = "Operación completada. Introduzca su PIN para una nueva transacción.";
                }
                else
                {
                    textBlockDisplay.Text = "No se retiraron billetes. Introduzca una cantidad válida.";
                }
            }
        }

        private void RealizarRetiro()
        {
            // Agrega lógica para realizar el retiro de dinero y ajustar la cantidad de billetes disponibles
            // Asegúrate de validar la cantidad disponible antes de realizar el retiro
        }

        private void ActualizarListaBilletes()
        {
            lbBilletes.Items.Clear();
            foreach (var kvp in billetesDisponibles)
            {
                lbBilletes.Items.Add($"{kvp.Key} euros: {kvp.Value} billetes");
            }
        }
    }
}
