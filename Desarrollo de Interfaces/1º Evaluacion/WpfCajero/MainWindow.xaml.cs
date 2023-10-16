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

        private String cadenaPin;
        private int contadorPIN = 0;

        private Boolean sesionIniciada = false;

        private List<int> billetesRetirados = new List<int>();

        public MainWindow()
        {
            InitializeComponent();
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
            if (!String.IsNullOrEmpty(cadenaPin))
            {
                if (comprobarPin())
                {
                    textBlockDisplay.Text = "¡Bienvenido! Seleccione la cantidad a retirar.\n";
                    sesionIniciada = true;
                }
                else
                {
                    textBlockDisplay.Text = "PIN incorrecto. Intente nuevamente.";
                }
            }
            else
            {
                textBlockDisplay.Text = "PIN incorrecto. No se registro ningun caracter";
            }
        }

        private bool comprobarPin()
        {
            if (cadenaPin.Equals("0000"))
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        private void botonBorrar_Click(object sender, RoutedEventArgs e)
        {
            if (sesionIniciada)
            {
                textBlockDisplay.Text = "Seleccione la cantidad a retirar.\n";
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
            RecogerDinero();
        }

        private void RecogerDinero()
        {
            billetesRetirados.Clear();
            listBoxResultado.Items.Clear();

            int cantidadARetirar;
            if (int.TryParse(textBlockDisplay.Text, out cantidadARetirar))
            {
                foreach (var billete in billetesDisponibles.Keys)
                {
                    int cantidadDisponible = billetesDisponibles[billete];
                    int cantidadRetirada = cantidadARetirar / billete;

                    if (cantidadRetirada > 0)
                    {
                        if (cantidadRetirada <= cantidadDisponible)
                        {
                            listBoxResultado.Items.Add($"Retirados {cantidadRetirada} billetes de {billete} euros");
                            billetesDisponibles[billete] -= cantidadRetirada;
                            cantidadARetirar -= cantidadRetirada * billete;

                            for (int i = 0; i < cantidadRetirada; i++)
                            {
                                billetesRetirados.Add(billete);
                            }
                        }
                        else
                        {
                            listBoxResultado.Items.Add($"No hay suficientes billetes de {billete} euros disponibles");
                        }
                    }
                }

                if (cantidadARetirar == 0)
                {
                    textBlockDisplay.Text = "Retire su dinero. Gracias por usar nuestro cajero.";
                }
                else
                {
                    textBlockDisplay.Text = "No se pueden retirar los billetes solicitados.";
                    DevolverBilletesNoEntregados();
                }
            }
        }

        private void DevolverBilletesNoEntregados()
        {
            foreach (var billete in billetesRetirados)
            {
                billetesDisponibles[billete]++;
            }
        }
    }
}
