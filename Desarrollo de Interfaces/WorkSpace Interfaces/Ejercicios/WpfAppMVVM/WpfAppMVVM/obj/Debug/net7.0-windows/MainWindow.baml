   M S B A M L   `   `   `  ����E  AWpfAppMVVM, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null=#clr-namespace:WpfAppMVVM.ViewModelsWpfAppMVVM.ViewModels  5   	   'clr-namespace:WpfAppMVVM
WpfAppMVVM  5   	       WpfAppMVVM.MainWindow   5   	   W SPresentationCore, Version=7.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=7.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35e aSystem.Windows.Controls.Ribbon, Version=7.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089R NWindowsBase, Version=7.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35F 9http://schemas.microsoft.com/winfx/2006/xaml/presentation     5   	   R NSystem.Xaml, Version=7.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   2vm#clr-namespace:WpfAppMVVM.ViewModels     5   	   *localclr-namespace:WpfAppMVVM     5   	     � Title$  
MainWindow��5	   	   $	��450��6   $	��800��6)    � DataContext 5      -  'WpfAppMVVM.ViewModels.MainPageViewModel  5   
    � 	Resources 5       �� 
TargetType �� Property �� Value �� 
TargetType �� CornerRadius �� Padding	 �� Triggers
 P� Property P� Value �� 
TargetName��%=  '��       '�� l    �� 5   
   " ��6   .�5      ��  � ��
 �����6+   �� 5       � ��$ 10q�6'   �� 5       � ��$ 	10,0,10,0q�6(   �� 5       � ��$ 30(�6)   �� 5       �@�Cursor$
 Hand��6'   �� 5       � �� 5      �� 5      " ��6&   .R�5      �� $
��back��6!   $ 5��6-   # z��5   !   ���#212164#��z��5   !   #��z��5   !   ._�5      �� $��1��6/   #��z��5   /   #��z��5    /   5!      	 5"      P� 5#      
 �@J�IsMouseOver$
 True��6=   . �5$   "   �� $
 back��6?    � ��
 ��ح�6R   5%      5(      5*      5,      �� 5-   
   " ��6   .�5.      ��  � ��$ 48(�6)   �� 5/       � ��
 �����6+   �� 50       � ��$ 10q�6'   �� 51       �@��Padding$ 5q�6(   52      53      .��55      �  � RowDefinitions 59   
   �� 5:      $	��80* �6   �� 5;      $	��20* �6   .+�5B   
   �� 
�������$��1��6%   �� 5D   
   $��1��6   $��Right=�6"    �� Orientation$ 
Horizontal=�5E      .+�5F      �� -   5         ��Name$ BotonPrimero��5F      $	��↢��6+   ��5G      �� *PrimeroCommand+�� 5H      -   5        $ BotonAnterior��5H      $	��⎗��6,   ��5I      �� *AnteriorCommand+�� 5J      -   5        $ BotonSiguiente��5J      $	��⎘��6-   ��5K      �� *SiguienteCommand+�� 5L      -   5        $ BotonUltimo��5L      $	��↣��6*   ��5M      �� *UltimoCommand+5N      �� 5P   
   -   5        $ BotonCargar��5P      $��1��6   $
��🔄��63   $
��Left=�5R      
���������5Q      �� *CargarCommand+5S      �� 5U   
   -   5         ��Name$ 	spPersona��5U      $ Vertical=�6(    5V      �� *CurrentPersona+.+�5W      �� ��6   �� *Nombre+ �� StringFormat$ Nombre: {0}���� 5X      $��WrapWithOverflow=�5Y      ��5X      �� *	Apellidos+$ Apellidos: {0}��5Y      �� 5Z      ��6   �� *
Telefono+$ Teléfono: {0}��5[      �� 5]   
   $��1��6   $��Bottom=�5^      $��Center=�6+   ��5]      �� *
Posicion+5^   +   5b      5d      