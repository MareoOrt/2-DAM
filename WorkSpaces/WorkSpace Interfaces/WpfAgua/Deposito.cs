using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WpfAgua
{
    internal class Deposito
    {
        public Deposito()
        {
            Cantidad = 10;
        }
        #region otra forma
        /*
        private int _cantidad;

        public int Cantidad { get; set; }

        */
        #endregion

        public event EventHandler DepositoVacio;
        public event EventHandler CantidadCambiada;

        private int cantidad;

        public int Cantidad
        {
            get { return cantidad; }
            set 
            {
                #region con excepciones
                /*
                if (value > 10)
                {
                    throw new ArgumentException("Valor superior de 10");
                }
                if (value == 0)
                {
                    throw new ArgumentException("Depósito vacio");
                }
                */
                #endregion

                cantidad = value;
            
                if(cantidad == 0)
                {
                    DepositoVacio?.Invoke(this, new EventArgs());
                }
            }
        }

    }
}
