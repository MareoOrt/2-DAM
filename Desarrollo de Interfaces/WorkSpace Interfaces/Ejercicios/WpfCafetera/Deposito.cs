using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WpfCafetera
{
    internal class Deposito
    {
        public event EventHandler ReinicioTotal;

        public double Total { get; set; }
        public double Vuelta { get; set; }
        public double Precio { get; set; }
        public double[] MonedasValidas = { 2.0, 1.0, 0.5,0.2, 0.1 };

        public Deposito(double[] monedasValidas)
        {
            MonedasValidas = monedasValidas;
            Iniciar();
        }


        public Boolean Acumular(double moneda)
        {
            if (EsMonedaValida(moneda))
            {
                Total += moneda;
                return true;
            }
            else
            {
                return false;
            }
        }

        public double Devolver()
        {
            return Vuelta;
        }

        public void Iniciar()
        {
            Total = 0;
            Vuelta = 0;
            ReinicioTotal?.Invoke(this, EventArgs.Empty);
        }

        private bool EsMonedaValida(double moneda)
        {
            foreach (double m in MonedasValidas)
            {
                if (moneda == m)
                {
                    return true;
                }
            }
            return false;
        }

        internal bool Dispensar(double precio)
        {
            if (Total >= Precio)
            {
                Vuelta = Total - Precio;
                Total = 0;
                ReinicioTotal?.Invoke(this, EventArgs.Empty);
                return true;
            }
            return false;
        }
    }
}
