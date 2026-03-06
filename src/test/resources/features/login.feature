#language: es
  Característica: FUNCIONALIDAD DEL LOGIN

    ##@regresion
    Escenario: [CP01] Logueo de usuario
      Dado que la web este disponible
      Cuando ingrese su correo corporativo "gys31@banbif.com.pe"
      Y dar clic en el boton Siguiente
      E ingrese la clave "VIxuknlz6%"
      Y dar clic en el boton Inciar sesion

    #@regresion
    Escenario: [CP02] Gestion de campaña / Validacion de campos
      Dado que la web este disponible
      Cuando ingrese su correo corporativo "gys31@banbif.com.pe"
      Y dar clic en el boton Siguiente
      E ingrese la clave "VIxuknlz6%"
      Y dar clic en el boton Inciar sesion
      Entonces El usuario selecciona el modulo Campanas opcion Campana del sidebar
      Y Se muestra una tabla con el listado de campañas con los datos
        | Código             |
        | Nombre             |
        | Descripción        |
        | F. Inicio vigencia |
        | F. Fin vigencia    |
        | Estado             |
        | Acciones           |
      Y Arriba de la tabla aparecen las siguientes opciones
        | Buscar por nombre         |
        | Búsqueda avanzada         |
        | Exportar a Excel |
        | Agregar Campaña  |

    #@regresion
    Escenario: [CP03] Gestion de campaña / Búsqueda de campaña
      Dado que la web este disponible
      Cuando ingrese su correo corporativo "gys31@banbif.com.pe"
      Y dar clic en el boton Siguiente
      E ingrese la clave "VIxuknlz6%"
      Y dar clic en el boton Inciar sesion
      Entonces El usuario selecciona el modulo Campanas opcion Campana del sidebar
      Y el usuario ingresa un valor en el campo de busqueda "Patricia"
      Y selecciona el boton Buscar
      Y se muestran las campanas cuyos nombres coincidan con el valor ingresado

    #@regresion
    Escenario: [CP04] Gestion de campaña / Validación de modal de Agregar campaña
      Dado que la web este disponible
      Cuando ingrese su correo corporativo "gys31@banbif.com.pe"
      Y dar clic en el boton Siguiente
      E ingrese la clave "VIxuknlz6%"
      Y dar clic en el boton Inciar sesion
      Entonces El usuario selecciona el modulo Campanas opcion Campana del sidebar
      Y el usuario selecciona el boton Agregar campana
      Y Se muestra un modal con un formulario con los siguientes campos obligatorios
        | Código Campaña                  |
        | Nombre                          |
        | País                            |
        | Empresa                         |
        | F. Inicio de vigencia           |
        | F. Fin vigencia                 |
        | Área                            |
        | Permitir visualización por Área |
        | Descripción                     |

    #@regresion
    Escenario: [CP05] Gestion de campaña / Agregar campaña
      Dado que la web este disponible
      Cuando ingrese su correo corporativo "gys31@banbif.com.pe"
      Y dar clic en el boton Siguiente
      E ingrese la clave "VIxuknlz6%"
      Y dar clic en el boton Inciar sesion
      Entonces El usuario selecciona el modulo Campanas opcion Campana del sidebar
      Y el usuario selecciona el boton Agregar campana
      E ingresar el Codigo "CFF6656"
      E ingresar el Nombre "Patricia"
      Y seleccionar el Pais "Perú"
      E ingresar el Empresa "Banbif"
      E ingresar el Fecha de inicio de vigencia "01/01/2024"
      E ingresar el Fecha de fin de vigencia "01/01/2024"
      E ingresar el Area No editable "Comercial"
      E ingresar el Permitir visualizacion por area "Si"
      E ingresar el Descripcion "Campaña de prueba"
      Y seleccionar el boton Guardar

    #@regresion
    Escenario: [CP06] Gestion de campaña / Validacion de valor Agregar campaña
      Dado que la web este disponible
      Cuando ingrese su correo corporativo "gys31@banbif.com.pe"
      Y dar clic en el boton Siguiente
      E ingrese la clave "VIxuknlz6%"
      Y dar clic en el boton Inciar sesion
      Entonces El usuario selecciona el modulo Campanas opcion Campana del sidebar
      Y el usuario selecciona el boton Agregar campana
      Y no ingresa valor en los campos
      Y selecciona el botón Guardar
      Entonces se muestra mensaje de error "Dato requerido" por cada campo obligatorio
      Y no permite guardar la campana


    #@regresion
    Escenario: [CP07] Gestion de campaña / Validacion de Campaña existente
      Dado que la web este disponible
      Cuando ingrese su correo corporativo "gys31@banbif.com.pe"
      Y dar clic en el boton Siguiente
      E ingrese la clave "VIxuknlz6%"
      Y dar clic en el boton Inciar sesion
      Entonces El usuario selecciona el modulo Campanas opcion Campana del sidebar
      Y el usuario selecciona el boton Agregar campana
      Y el usuario ingresa un código de campana existente, completa los demás campos con datos válidos.
      Y selecciona Guardar
      Entonces se muestra mensaje: “El código de campaña ya existe”
      Y no se guarda la campana

    #@regresion
    Escenario: [CP08] Gestion de campaña / Agregar campaña
      Dado que la web este disponible
      Cuando ingrese su correo corporativo "gys31@banbif.com.pe"
      Y dar clic en el boton Siguiente
      E ingrese la clave "VIxuknlz6%"
      Y dar clic en el boton Inciar sesion
      Entonces El usuario selecciona el modulo Campanas opcion Campana del sidebar
      Y el usuario selecciona el boton Agregar campana
      Entonces El usuario completa todos los campos requeridos
      Y selecciona Guardar
      Y Se muestra mensaje de éxito
      Entonces Se guarda la campaña
      Y El modal se cierra o limpia (según diseño)
      Y La campaña aparece inmediatamente en la grilla

    #@regresion
    Escenario: [CP09] Gestion de campaña / Agregar campaña
      Dado que la web este disponible
      Cuando ingrese su correo corporativo "gys31@banbif.com.pe"
      Y dar clic en el boton Siguiente
      E ingrese la clave "VIxuknlz6%"
      Y dar clic en el boton Inciar sesion
      Entonces El usuario selecciona el modulo Campanas opcion Campana del sidebar
      Y el usuario selecciona el boton Agregar campana








