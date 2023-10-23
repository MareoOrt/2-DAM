using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace wpfDispensadorAgua
{
    internal class Deposito
    {
		private int cantidad;

        public int Cantidad
		{
			get { return cantidad; }
			set { 
				if(value > 10)
				{
                    throw new ArgumentException(""	
                }
				}
		}



	}
}
