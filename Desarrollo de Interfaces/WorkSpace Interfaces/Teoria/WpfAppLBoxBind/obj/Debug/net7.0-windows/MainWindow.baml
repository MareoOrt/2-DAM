   M S B A M L   `   `   `  ����I  EWpfAppLBoxBind, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null/clr-namespace:WpfAppLBoxBindWpfAppLBoxBind  5   	       WpfAppLBoxBind.MainWindow   5   	   W SPresentationCore, Version=7.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=7.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35e aSystem.Windows.Controls.Ribbon, Version=7.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089R NWindowsBase, Version=7.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35F 9http://schemas.microsoft.com/winfx/2006/xaml/presentation     5   	   R NSystem.Xaml, Version=7.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   .localclr-namespace:WpfAppLBoxBind     5   	     � Language$  es-ES�5   	    � Title$ 
MainWindow��5	   	   $	��450��6   $	��800��6)    � DataContext 5
        WpfAppLBoxBind.Datos  5   
    � 	Resources 5         ct1 �� 
TargetType �� CornerRadius K� IsItemsHost  estiloListBoxItem �� 
TargetType �� Property	 �� Value
 ��Name �� Padding �� Triggers P� Property P� Value �� 
TargetName3  -System.Windows.Media.Effects.DropShadowEffect   
BlurRadius   ShadowDepth��%/  &	        &	 �     �� 5   
   " ��6&   .R�5      �� 
��� ���$ 20��6)   ._�5      �� $��10q�6   $
��Auto=�6+   .��5      �� $��0q�6!   $
 True��6,   5      5      5      �� 5   
   " ��6*   .�5      ��  � ��	 5      �� 5      " ��6&   .R�5      �� $
 borde��6!   $��Stretch=�60   $��2q�5   !   $ 10q�5   !   
������$ 6��5   !   ._�5      �� 5       5       P� 5!       �@J�IsMouseOver$
 True��6=   . �5"   "   �� $ borde��6)    � ��
	 ��ح�6R   5#      P� 5$       �@��
IsSelected$
 True��6<   . �5%   "   �� $ borde��6)    �@J�Effect	 5&   &     5'   *   $ 7Q�6;   $ 3Q�6J   5)   #   �� 5*   "   $ borde��6)    � ��
	 ��ح�6R   5+      5-      5/      50      51      .��52      � -   5         �Name$ grid1��52       � RowDefinitions 53   
   �� 54      $��5* �6   �� 55      $��* �6    � ColumnDefinitions 57   
   �� 58      $��* �6   �� 59      $��* �6   �� 5:      $��* �6   .+�5<   
   �� -   5         ��Name$ 
lbPersonas��5<   "   $��1��6   #��[  5>      #��[ 5?       �� DisplayMemberPath$ Nombre��5@       �� SelectedValuePath$ Telefono��5A      ��5=      �� *
Personas+5B      (� 5D   
   $��0��6   $��2��6   $��2��6/   $��7q�5E      .��5F      ��  6   ��  �� ElementName$ 
lbPersonas�� �� Path$ SelectedItem�.+�5H      �� ��6   �� *Nombre+�� 5J      $��Center=�5K      $��Center=�65   ��5J      �� *SelectedValue+$ 
lbPersonas��5K   5   5L      5M      � 5N   
   $��5q�6   $��1��6   
����ح�$��3��6>    5O      �� 5P      $��* �6#   �� 5Q      $��* �6#   �� 5R      $��* �6#   .+�5T      �� -   5        $��Añadir persona��5T      $��1��6.   $��10q�5U      $
��15 0q�6!   $
��Left=�60   5V      �� 5W      -   5         ��Name$ botonSeleccionar��5W      $��1��6/   $��10q�5X      $
��15 0q�6!   $��Mostrar detalle��5Y      $��Right=�6/   5Z      5[      �� 5\   
   -   5         ��Name$
 foto��5\      $��15q�6   $��UniformToFill=�5]      ��5^      �� $ 
lbPersonas��$ SelectedItem.Foto�5_      5a      