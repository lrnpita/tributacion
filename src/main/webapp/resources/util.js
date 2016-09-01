
 	$(document).ready(function() {
 
/**
 * Calcular campos de la vista Compra Datos
 */ 		
 		/*
 	 	* se calculan los campos para el detalle de la compra
 	 	*/
 		// calcular base no objeto de iva en detalles de compra
 		$(document).on("blur","#baseNoObjetoIva",function(){
 			calcularBaseNoObjetoIva($(this));
 			calcularTotalDocumento();
 		}); 
 		$(document).on("blur","#baseImpobible0",function(){
 			calcularRIRbaseImp0($(this));
 			calcularTotalDocumento();
 		}); 
 		$(document).on("blur","#baseImponible12",function(){
 			calcularRIRbaseImp12($(this));
 			calcularTotalDocumento();
 		}); 
 		$(document).on("blur","#porcentajeIva",function(){
 			var baseImp12 = $(this).parent('td').parent('tr').find('input#baseImponible12');
 			var porcentajeIva = $(this);
 			// calcular valor IVA
 			var valorIva = (baseImp12.val()*porcentajeIva.val())/100;
 			$(this).parent('td').parent('tr').find('input#RIvalorIva').val(valorIva);
 		});
 		 		
 		$(document).on("blur","#RIret",function(){
 			var RIvalorIva = $(this).parent('td').parent('tr').find('input#RIvalorIva');
 			//calcular retncionIva
 			var RIret = $(this).parent('td').parent('tr').find('input#RIret');
 			var RIretIva = $(this).parent('td').parent('tr').find('input#RIretIva');
 			var retIva = (RIvalorIva.val()*RIret.val())/100;
 			RIretIva.val(retIva);
 			
 			calcularTotalRetenido();
 		}); 
 		$(document).on("blur","#RIRret",function(){
 			var RIRbaseImp12 = $(this).parent('td').parent('tr').find('input#RIRbaseImp12');
 			//calcular retencionIR
 			var RIRretIr = $(this).parent('td').parent('tr').find('input#RIRretIr');
 			var RIRret = $(this).parent('td').parent('tr').find('input#RIRret');
 			var retIR = (RIRbaseImp12.val()*RIRret.val())/100;
 			RIRretIr.val(retIR);
 			
 			calcularTotalRetenido();
 		}); 	
  		
 		$("#iceImpuesto").blur(function(){
 			calcularTotalDocumento();
 		});
 		
 		$("#otrosImpuestos").blur(function(){
 			calcularTotalDocumento();
 		});
 		
 		// desabilidando los campos del comprobante modificado de la vista compra
 		$("#tipoComprobante").change(function() {
 			if ($("#tipoComprobante").val() == '04' || $("#tipoComprobante").val() == '05') {
 				$("#tipoComprobanteModificado").removeAttr('disabled');
 				$("#fechaEmisionModificado").removeAttr('disabled');
 				$("#serieModificado").removeAttr('disabled');
 				$("#secuenciaModificado").removeAttr('disabled');
 				$("#codigoAutorizacionModificado").removeAttr('disabled');
 			} else {
 				$("#tipoComprobanteModificado").attr('disabled', 'disabled').val('');
 				$("#fechaEmisionModificado").attr('disabled', 'disabled').val('');
 				$("#serieModificado").attr('disabled', 'disabled').val('');
 				$("#secuenciaModificado").attr('disabled', 'disabled').val('');
 				$("#codigoAutorizacionModificado").attr('disabled', 'disabled').val('');
 			}
 		}).trigger('change');	
 		
 		
 	//poner esto cuando el sistema no sea para la UCE(cuando no se carguen las compras del xml)
 	// buscar porciento de retención de impuesto a la renta según el tipo de transacción en la compra
 /*		$(document).on("change","#tipoTransaccion",function(){
 			var	codigo = $(this).val();
 			var idProveedor = $("#idProveedorCompra").val();
 			
 				$.ajax({
 					url : window.location.pathname + "/porcentajeIR",
 					type : "POST",
 					contentType : 'application/json',
 					mimeType: 'application/json',
 					dataType : 'json',
 					data :  {codigo : codigo},
 					success : function(response) {
 						$(this).parent('td').parent('tr').find('input#RIRret').val(response);
 					},
 					error : function(xhr, status, error) {
 						alert(xhr.responseText);
 					}
 				});
 			
 				if(idProveedor != ""){
 					// buscar porciento de retención de iva en la compra
 					$.ajax({
 						url : window.location.pathname + "/porcentajeIva",
 						type : "POST",
 						contentType : 'application/json',
 						mimeType: 'application/json',
 						dataType : 'json',
 						data :  {idProveedor : idProveedor, codigo : codigo},
 						success : function(response) {
 							$(this).parent('td').parent('tr').find('input#RIret').val(response);
 							alert('res' + response + " " + $(this).parent('td').parent('tr').find('td input#RIret').val());
 							
 						},
 						error : function(xhr, status, error) {
 							alert(xhr.responseText);
 						}
 					});
 					
 				}	
 		});
*/		
		// Se busca el nombre del proveedor al escribir el id en la vista compra/datos
 		$("#idProveedorCompra").blur(function(){
 			idProveedor = $(this).val();
 			if(idProveedor != ""){
 				BuscarNombreProveedor()
 			}
 		});
 		
 		// poniendo por defecto Ecuador en el select pais.
 		$("#pais option[value=593]").attr("selected",true);
 		
	     // desabilidando los campos según tipo de pago
   /*     var $tipoPagoSegunLugar = $('#tipoPagoSegunLugar'), $convenio = $('#convenio'), $normaLegal = $('#normaLegal'); 
        $tipoPagoSegunLugar.change(function() {
 			if ($tipoPagoSegunLugar.val() == '01') {
 				$convenio.attr('disabled', 'disabled').val('');
 				$normaLegal.attr('disabled', 'disabled').val('');
 			} else {
 				$convenio.removeAttr('disabled');
 				$normaLegal.removeAttr('disabled');
 			}
 		}).trigger('change');  */
 		
 /**
  * Calcular campos de la vista Importacion Datos 
  */		
 		
 		$("#porcentajeIva").blur(function(){
 			// aqui se hallar el % de retIva
 			var totalRI = (parseFloat($("#baseImponibleGravada").val()) * parseFloat($("#porcentajeIva").val()))/100;
 			$("#iva").val(totalRI);
 			});
 		
 		$("#baseImponibleGravada").blur(function(){
 			// aqui se hallar el % de retIva
 			var totalRI = (parseFloat($("#baseImponibleGravada").val()) * parseFloat($("#porcentajeIva").val()))/100;
 			$("#iva").val(totalRI);
 			});
 		
 		$("#porcentajeIce").blur(function(){
			// aqui se suman los campos para obtener el % de retIce
			var totalRice = parseFloat($("#baseImponibleIce").val()) * parseFloat($("#porcentajeIce").val())/100;
			$("#ice").val(totalRice);
 			});
 		
 		$("#baseImponibleIce").blur(function(){
			// aqui se suman los campos para obtener el % de retIce
			var totalRice = parseFloat($("#baseImponibleIce").val()) * parseFloat($("#porcentajeIce").val())/100;
			$("#ice").val(totalRice);
 			});
 		
 		$("#porcentajeRetencion").blur(function(){
			// se suman los campos para obtener el % de valor retenrido de RIR
			var totalRice = parseFloat($("#baseImponibleRet").val()) + parseFloat($("#porcentajeRetencion").val());
			$("#valorRet").val(totalRice);
 			});
 		
 		$("#baseImponibleRet").blur(function(){
			// se suman los campos para obtener el % de valor retenrido de RIR
			var totalRice = parseFloat($("#baseImponibleRet").val()) + parseFloat($("#porcentajeRetencion").val());
			$("#valorRet").val(totalRice);
 			});
 		
 	// buscar porciento de retención de impuesto a la renta según el tipo de transacción en importacion
 		$("#impTipoTransaccion").change(function() {
 			var codigo = $("#impTipoTransaccion").val();
			
			$.ajax({
				url : window.location.pathname + "/porcentajeIR",
				type : "POST",
				contentType : 'application/json',
				mimeType: 'application/json',
				dataType : 'json',
				data :  {codigo : codigo},

				success : function(response) {
					$("#porcentajeRetencion").val(response);
				},
				error : function(xhr, status, error) {
					alert(xhr.responseText);
				}
			});
		}).trigger('change');	
 		

 /**
  * útiles
  */	
 		// mostando nombres de los li en el sidebar
 		$(".main-menu").hover(function() {
 			$('span.nav-text').css('visibility', 'visible');
 		}, function() {
 			$('span.nav-text').css('visibility', 'hidden');
 		});
 		
 		// poner por defecto el valor del porcentaje de IVA 
	    $('.porcentIva').each(function(){
	        $(this).val(14);
	    });
 		
 		// Se busca el nombre del proveedor al escribir el id en la vista compra/datos
 		$("#idClienteVenta").blur(function(){
 			idCliente = $(this).val();
 			if(idCliente != ""){
 				BuscarNombreCliente()
 			}
 		});
 		
 		// convertir en mayusculas
 		mayuscula("input#nombre"); 
 		mayuscula("input#direccion"); 
 		mayuscula("input#apellidos");
 		// convertir a minusculas
 		minuscula("input#emailproveedor");
 		minuscula("input#emailcliente");
 		minuscula("input#email");
 		
 		// esto es para la paginación
 		$('#paginar').dataTable( {
 	        "language": {
 	        	"url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
 	        }
 	    });
 	
 		// para cambiar el action del formulario reportes
	 	$("#generarReporte").on('click', function(event) {
	 	 	   $("#reporteForm").attr('action', "reportes/" + $('#selectTipoReporte').val());
	 	 	   $("#reporteForm").submit();
	 	});
	 	
 		// para cambiar el action del formulario anexos
	 	$("#generarAnexo").on('click', function(event) {
	 	 	   $("#anexoForm").attr('action', "anexos/" + $('#selectTipoAnexo').val());
	 	 	   if($('#selectTipoAnexo').val() == 107){
	 	 		 $("#anexoForm").attr('method', "get"); 
	 	 	   } else {
	 	 		 $("#anexoForm").attr('method', "post"); 
	 	 	   }
	 	 	   $("#anexoForm").submit();
	 	});
	 	
	 	// para validar y enviar el formulario
	 	
	 	// validar el formulario antes de ser enviado
	 	$("#myForm").validate();
	 	$("#anexoForm").validate();
	 	$("#reporteForm").validate();
	 	$("#frmBuscar").validate();
	 	
		$("#myButton").on('click', function(event) {
			$("#myForm").validate();
			var $inputRuc = $('#ruc');
			var $tipoId = $('#tipoIdentificacion');
			var expregRuc = /^[0-9]{10}001$/;
			var expregCedula = /^[0-9]{10}$/;
			var expregConsumidorFinal = /^9{13}$/;
			
			if($tipoId.val() == '04'){
				if (document.getElementById('ruc') != null) {
					if (!expregRuc.test($inputRuc.val())) {
						$inputRuc.attr('class', 'form-control error').val();
						var node = document.createElement("label");               
						var textnode = document.createTextNode("Identificación incorrecta"); 
						node.appendChild(textnode);
						$inputRuc.parent.appendClild(node);
						return;
					} 
				}
			} else { // si es 05 es cédula
				if($tipoId.val() == '05'){
					if (document.getElementById('ruc') != null) {
						if (!expregCedula.test($inputRuc.val())) {
							$inputRuc.attr('class', 'form-control error').val();
							('<label>Cédula incorrecta</label>').appendTo('#ruc');
							return;
						} 
					}
				} else { // si es 05 es cédula
					if($tipoId.val() == '07'){
						if (document.getElementById('ruc') != null) {
							if (!expregConsumidorFinal.test($inputRuc.val())) {
								$inputRuc.attr('class', 'form-control error').val();
								('<label>Indentificación incorrecta para consumidor final</label>').appendTo('#ruc');
								return;
							} 
						}
					}
				}
			} 
			
				enviarFormulario();
		});

		function enviarFormulario() {
			var $form = $("#myForm");
			if ($form.valid()) {
				$form.submit();
			} 
		}
		
		// Cambiar select pais de residencia en la vista trabajador 
		var $residencia = $('#residencia'), $pais = $('#pais'); 
		
	        $residencia.change(function() { 
	        	if ($residencia.val() == '1'){
	            	$("#pais option[value=593]").attr("selected",true); 
	            	$pais.removeAttr('disabled');
	        	}
	        	else{
	            	$("#pais option[value=1]").attr("selected",true); 
	            	$pais.removeAttr('disabled');	
	        	}
	        }).trigger('change');  
	        
	     // action para el botón nuevo   
			$("#btn-nuevo").on('click',
				    function(event) {
				var url = window.location.pathname;
				this.href= url + "/datos";
				});
			
		// action para el botón buscarRetencion   
			$("#buscarRetencion").on('click',
				    function(event) {
				var url = window.location.pathname;
				this.href= url + "/buscar?fecha=" + $("#fecha").val();
				});
			
		//	action para el botón cancelar
			$("#cancelar").on('click',
				    function(event) {
				this.href= document.referrer;		
			});
			
		// action para el botón editar	
			$("#btn-editar").on('click', function(event) {
				if ($("input[type=checkbox]:checked").val() != undefined) {
					var id = $("input[type=checkbox]:checked").val();
					var url = window.location.pathname;
					this.href = url + "/editar?id=" + id;
				} else {
					$("#alert-error-editar").show();
					$("#alert-error-editar").fadeOut(5000);
				}
			});
			
		// action para el botón eliminar----------falta la confirmación y el cartel de success 
	/*		$("#btn-eliminar").on('click', function(event) {
				if ($("input[type=checkbox]:checked").val() != undefined) {
					var id = $("input[type=checkbox]:checked").val();
					var url = window.location.pathname;
					this.href = url + "/eliminar?id=" + id;
				} else {
					$("#alert-error-eliminar").show();
					$("#alert-error-eliminar").fadeOut(5000);
				}
			});*/
			
	// eliminar todos los elementos seleccionados
			$("#btn-eliminar").on('click', function(event) {
				
				if ($('input[class="check"]:checked').val() != undefined) {
						
					var values = $('input[class="check"]:checked').map(function () {
						  return this.value;
							}).get();
						$.ajax({
								url : window.location.pathname + "/eliminar",
								type : "POST",
								contentType : 'application/json',
								mimeType: 'application/json',
								dataType : 'json',
								data :  JSON.stringify(values),
	
								success : function(response) {
									if(response == true){
										$("#alert-eliminado-varios").show();
										$("#alert-eliminado-varios").fadeOut(5000);
										// recargar solo la tabla
										$("#paginar").load(window.location.pathname + " #paginar");
									} else {
										$("#alert-error-eliminado-varios").show();
										$("#alert-error-eliminado-varios").fadeOut(5000);
										$("#paginar").load(window.location.pathname + " #paginar");
									}
								},
								error : function(xhr, status, error) {
									alert(xhr.responseText);
								}
							}); 
				} else {
					$("#alert-error-editar").show();
					$("#alert-error-editar").fadeOut(5000);
				}
			});	

		// Mostrar modal de confirmación al eliminar
			$('a[data-confirm]').click(function(ev) {
				var href = $(this).attr('href');
				if (!$('#dataConfirmModal').length) {
					$("#dataConfirmModal").show();
				} 
				$('#dataConfirmModal').find('.modal-body').text($(this).attr('data-confirm'));
				$('#dataConfirmOK').attr('href', href);
				$('#dataConfirmModal').modal({show:true});
				return false;
			});
			
		 // agregando y eliminando filas de la tabla de productos de la vista ventas
		 // Clona la fila oculta que tiene los campos base, y la agrega al final de la tabla
		ButtonAdicionarProductosVenta();
			
		// agregando y eliminando filas de la tabla de detalles de la vista compras
		// Clona la fila base que tiene los campos base, y la agrega al final de la tabla	
		ButtonAdicionarDetalleCompra();
		
		// Evento que selecciona la fila y la elimina
		ButtonEliminarFila();
		
		// poniendo el precio total en la tabla venta 
			$(document).on("blur","#cantidad",function(){
	 		    calcularSubTotalProducto($(this));
				calcularPrecioTotalProducto($(this));
	 		 }); 
			
			$(document).on("blur","#tDescuento",function(){
	 		    calcularPrecioTotalProducto($(this));
			}); 
		 		
	// poniendo el monthpiker con jquery al input mes de la vista reportes   
		    $('.monthPiker').datepicker( {
		        changeMonth: true,
		        changeYear: true,
		        showButtonPanel: true,
		        dateFormat: 'MM yy',
		        onClose: function(dateText, inst) { 
		            var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
		            var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
		            $(this).datepicker('setDate', new Date(year, month, 1));
		        }
		    });
	
	// poner a todos los input tipo datepiker con jquery 
		    $('.datePiker').each(function(){
		        $(this).datepicker({
		        	changeMonth: true,//this option for allowing user to select month
			 	       changeYear: true
		        });
		        
		        var hoy = new Date();
		        var dd = hoy.getDate();
		        var mm = hoy.getMonth()+1; //hoy es 0!
		        var yyyy = hoy.getFullYear();

		        if(dd<10) {
		            dd='0'+dd
		        } 

		        if(mm<10) {
		            mm='0'+mm
		        } 

		        hoy = mm+'/'+dd+'/'+yyyy;
		        
		        // se pone por defecto la fecha actual
		        if($(this).val() ==  ""){
		        	 $(this).val(hoy);
		        }
		       
		    });
		    
	// dessabilitando el input fecha cuando se seleccione el anexo 107    
	 		$("#selectTipoAnexo").change(function(){
				if($("#selectTipoAnexo").val() == "107"){
					$("#fecha").attr('disabled', 'disabled').val('');
				}else
					$("#fecha").removeAttr('disabled');
	 			});
	 		
	// calculando campos en la vista venta/datos 		
	 		$("#retencionIva").blur(function(){
	 			var totalRetenido =parseFloat($("#retencionIR").val()) + parseFloat($("#retencionIva").val());
	 			//aqui va el porciento calculado
	 			$("#totalRetencion").val(totalRetenido);
	 			});
	 		$("#retencionIR").blur(function(){
	 			var totalRetenido =parseFloat($("#retencionIR").val()) + parseFloat($("#retencionIva").val());
	 			//aqui va el porciento calculado
	 			$("#totalRetencion").val(totalRetenido);
	 			});
		    
	 		
	 	
/**
 * Desabilitar campos de la vista Trabajador datos
 */
	 		// desabilitando campos en dependencia de la condici'on del trabajador
			var $condicion = $('#condicion'), $tipoNewTrabajador = $('#tipoNewTrabajador'), $porcentaje = $('#porcentaje'), $idNewTrabajador = $('#idNewTrabajador');
			$condicion.change(function() {
				if ($condicion.val() == '1') {
					$tipoNewTrabajador.attr('disabled', 'disabled').val('');
					$porcentaje.attr('disabled', 'disabled').val('');
					$idNewTrabajador.attr('disabled', 'disabled').val('');
				} else{
				if ($condicion.val() == '2'){
					$tipoNewTrabajador.attr('disabled', 'disabled').val('');
					$porcentaje.removeAttr('disabled');
					$idNewTrabajador.attr('disabled', 'disabled').val('');
				} else{
					$tipoNewTrabajador.removeAttr('disabled');
					$porcentaje.removeAttr('disabled');
					$idNewTrabajador.removeAttr('disabled');
				}
				}
					
			}).trigger('change');	
		
			//desabilitando campos en dependencia de la residencia
			var $residencia = $('#residencia'), $convenio = $('#convenio');
			$residencia.change(function() {
				if ($condicion.val() == '1') {
					$convenio.attr('disabled', 'disabled').val('');
				} else{
					if ($residencia.val() == '2'){
						$convenio.removeAttr('disabled');
					} 
				}
			}).trigger('change');

/**
* Calcular campos de la vista anexo 103
*/
			$("#c898").change(function(){
	 			$("#c890").val(parseFloat($("#c898").val()) + parseFloat($("#c897").val()) + parseFloat($("#c899").val()));
	 			$("#c902").val(parseFloat($("#c499").val()) - parseFloat($("#c890").val()));
	 			$("#c999").val(parseFloat($("#c903").val()) + parseFloat($("#c904").val()) + parseFloat($("#c902").val()));
	 			});	
			
			$("#c897").change(function(){
	 			$("#c890").val(parseFloat($("#c898").val()) + parseFloat($("#c897").val()) + parseFloat($("#c899").val()));
	 			$("#c902").val(parseFloat($("#c499").val()) - parseFloat($("#c890").val()));
	 			$("#c999").val(parseFloat($("#c903").val()) + parseFloat($("#c904").val()) + parseFloat($("#c902").val()));
	 			});
			
			$("#c899").change(function(){
	 			$("#c890").val(parseFloat($("#c898").val()) + parseFloat($("#c897").val()) + parseFloat($("#c899").val()));
	 			$("#c902").val(parseFloat($("#c499").val()) - parseFloat($("#c890").val()));
	 			$("#c903").val(parseFloat($("#c903").val()) + parseFloat($("#c902").val()));
	 			$("#c904").val(parseFloat($("#c904").val()) * parseFloat($("#c902").val()));
	 			$("#c999").val(parseFloat($("#c903").val()) + parseFloat($("#c904").val()) + parseFloat($("#c902").val()));
	 			});
			
			// de aquí para abajo funcionan para el formulario 103 y 104
			$("#c903").change(function(){
	 			$("#c999").val(parseFloat($("#c903").val()) + parseFloat($("#c904").val()) + parseFloat($("#c902").val()));
	 			$("#c905").val(parseFloat($("#c999").val()) - parseFloat($("#c907").val()));
	 			});	
			
			$("#c904").change(function(){
	 			$("#c999").val(parseFloat($("#c903").val()) + parseFloat($("#c904").val()) + parseFloat($("#c902").val()));
	 			$("#c905").val(parseFloat($("#c999").val()) - parseFloat($("#c907").val()));
	 			});
			
			$("#c909").change(function(){
	 			$("#c907").val(parseFloat($("#c909").val()) + parseFloat($("#c911").val()) + parseFloat($("#c913").val()) + parseFloat($("#c915").val()));
	 			$("#c905").val(parseFloat($("#c999").val()) - parseFloat($("#c907").val()));
	 			});
			
			$("#c911").change(function(){
	 			$("#c907").val(parseFloat($("#c909").val()) + parseFloat($("#c911").val()) + parseFloat($("#c913").val()) + parseFloat($("#c915").val()));
	 			$("#c905").val(parseFloat($("#c999").val()) - parseFloat($("#c907").val()));
	 			});
			
			$("#c913").change(function(){
	 			$("#c907").val(parseFloat($("#c909").val()) + parseFloat($("#c911").val()) + parseFloat($("#c913").val()) + parseFloat($("#c915").val()));
	 			$("#c905").val(parseFloat($("#c999").val()) - parseFloat($("#c907").val()));
	 			});
			
			$("#c915").change(function(){
	 			$("#c907").val(parseFloat($("#c909").val()) + parseFloat($("#c911").val()) + parseFloat($("#c913").val()) + parseFloat($("#c915").val()));
	 			$("#c905").val(parseFloat($("#c999").val()) - parseFloat($("#c907").val()));
	 			});
			
/**
*  Calcular campos de la vista anexo 104
*/
			$("#c480").change(function(){
				$("#c484").val(parseFloat($("#c480").val())*12/100);
				$("#c485").val(parseFloat($("#c482").val()) - parseFloat($("#c484").val()));
				$("#c499").val(parseFloat($("#c483").val()) + parseFloat($("#c484").val()));
				
				if((parseFloat($("#c499").val()) - parseFloat($("#c564").val())) > 0){
					$("#c601").val(parseFloat($("#c499").val()) - parseFloat($("#c564").val()));
					calcularCampo619Anexo104();
					$("#c615").val(parseFloat($("#c601").val()) - parseFloat($("#c605").val()));
				} else {
					$("#c602").val(parseFloat($("#c499").val()) - parseFloat($("#c564").val()));
					calcularCampo619Anexo104();
					$("#c615").val(parseFloat($("#c602").val()) - parseFloat($("#c605").val()));
				}
			});	
			
			$("#c605").change(function(){
				$("#c615").val((parseFloat($("#c602").val()) + parseFloat($("#c601").val())) - parseFloat($("#c605").val()));
				calcularCampo619Anexo104();
			});	
			
			$("#c483").change(function(){
				$("#c499").val(parseFloat($("#c483").val()) + parseFloat($("#c484").val()));
			});	
			
			$("#c607").change(function(){
				$("#c617").val(parseFloat($("#c607").val()) + parseFloat($("#c609").val()));
				calcularCampo619Anexo104();
			});
			
			$("#c621").change(function(){
				$("#c699").val(parseFloat($("#c619").val()) + parseFloat($("#c621").val()));
				$("#c859").val(parseFloat($("#c699").val()) + parseFloat($("#c801").val()));
				$("#c902").val(parseFloat($("#c859").val()) - parseFloat($("#c898").val()));
			});
			
			$("#c609").change(function(){
				calcularCampo619Anexo104();
			});	
			$("#c611").change(function(){
				calcularCampo619Anexo104();
			});	
			$("#c612").change(function(){
				calcularCampo619Anexo104();
			});	
			$("#c613").change(function(){
				calcularCampo619Anexo104();
			});	
			
			$("#c800").change(function(){
				$("#c801").val(parseFloat($("#c799").val()) - parseFloat($("#c800").val()));
				$("#c859").val(parseFloat($("#c699").val()) + parseFloat($("#c801").val()));
				$("#c902").val(parseFloat($("#c859").val()) - parseFloat($("#c898").val()));
			});	
			
			$("#c897").change(function(){
				$("#c890").val(parseFloat($("#c897").val()) + parseFloat($("#c898").val()) + parseFloat($("#c899").val()));
			});
			$("#c898").change(function(){
				$("#c890").val(parseFloat($("#c897").val()) + parseFloat($("#c898").val()) + parseFloat($("#c899").val()));
				$("#c902").val(parseFloat($("#c859").val()) - parseFloat($("#c898").val()));
			});
			$("#c899").change(function(){
				$("#c890").val(parseFloat($("#c897").val()) + parseFloat($("#c898").val()) + parseFloat($("#c899").val()));
			});
			
			$("#c917").change(function(){
				$("#c906").val(parseFloat($("#c917").val()) + parseFloat($("#c919").val()));
			});
			$("#c919").change(function(){
				$("#c906").val(parseFloat($("#c917").val()) + parseFloat($("#c919").val()));
			});
	 
			// habilitando campos
			$("#c104").change(function(){
				if($("#c104").val() != 0){
					$("#c897").removeAttr('readonly');
					$("#c898").removeAttr('readonly');
					$("#c899").removeAttr('readonly');
					$("#c880").removeAttr('readonly');
				} else {
					$("#c897").attr('readonly', 'readonly').val('');
					$("#c898").attr('readonly', 'readonly').val('');
					$("#c899").attr('readonly', 'readonly').val('');
					$("#c880").attr('readonly', 'readonly').val('');
				}
				
				$("#c897").val(0.0);
				$("#c898").val(0.0);
				$("#c899").val(0.0);
				$("#c880").val(0.0);
			})
 	
/**
 * Calcular campos de la vista Movimiento de trabajadores- datos
 */
			// calcular gastos personales
	 		$("#vestimenta").blur(function(){
	 			$("#mvestimenta").val((parseFloat($("#vestimenta").val()))/12);
	 			totalGastosPersonales();
	 			calcularImpuestoRentaCausado();
	 		});
	 			
	 	 	$("#vivienda").blur(function(){
	 	 		$("#mvivienda").val((parseFloat($("#vivienda").val()))/12);
	 	 		totalGastosPersonales();
	 	 		calcularImpuestoRentaCausado();
	 	 		});
	 			
	 	 	$("#salud").blur(function(){
	 	 		$("#msalud").val((parseFloat($("#salud").val()))/12);
	 	 		totalGastosPersonales();
	 	 		calcularImpuestoRentaCausado();
	 	 		});
	 	 	
	 	 	$("#educacion").blur(function(){
	 	 		$("#meducacion").val((parseFloat($("#educacion").val()))/12);
	 	 		totalGastosPersonales();
	 	 		calcularImpuestoRentaCausado();
	 	 		});
	 	 	
	 	 	$("#alimentacion").blur(function(){
	 	 		$("#malimentacion").val((parseFloat($("#alimentacion").val()))/12);
	 	 		totalGastosPersonales();
	 	 		calcularImpuestoRentaCausado();
	 	 		});	
	 	 	
	 	 	$("#sueldosYSalarios").blur(function(){
	 			calcularImpuestoRentaCausado();
	 		});
	 	 	$("#sobresueldos").blur(function(){
	 			calcularImpuestoRentaCausado();
	 		});
	 	 	$("#participacionUtilidades").blur(function(){
	 			calcularImpuestoRentaCausado();
	 		});
	 	 	$("#ingresosOtrosEmpleadores").blur(function(){
	 			calcularImpuestoRentaCausado();
	 		});
	 	 	$("#irAsumidoOtrosEmpleadores").blur(function(){
	 			calcularImpuestoRentaCausado();
	 		});
	 	 	$("#decimotercerSueldo").blur(function(){
	 			calcularImpuestoRentaCausado();
	 		});
	 	 	$("#decimocuartoSueldo").blur(function(){
	 			calcularImpuestoRentaCausado();
	 		});
	 	 	$("#fondosReserva").blur(function(){
	 			calcularImpuestoRentaCausado();
	 		});
	 	 	$("#salarioDigno").blur(function(){
	 			calcularImpuestoRentaCausado();
	 		});
	 	 	$("#otrosIngresosNoGravados").blur(function(){
	 			calcularImpuestoRentaCausado();
	 		});
	 	 	$("#IngGravadosEmpleador").blur(function(){
	 			calcularImpuestoRentaCausado();
	 		});
	 	 	$("#iessOtrosEmpleadores").blur(function(){
	 			calcularImpuestoRentaCausado();
	 		});
	 	 	$("#aportePersonalEmpleadorActual").blur(function(){
	 			calcularImpuestoRentaCausado();
	 		});
	 		$("#mesesTrabajados").change(function(){
	 			calcularImpuestoRentaCausado();
	 		});
	 		$("#mesesTrabajados").change(function(){
	 			calcularImpuestoRentaCausado();
	 		});
	 		$("#retencionOtrosEmpleadores").blur(function(){
	 			calcularRetencionMensual();
	 		});
	 		$("#irAsumidoEmpleadorActual").blur(function(){
	 			calcularRetencionMensual();
	 		});
	 		
	 		
	 	// Calculando exoneraciones en el movimiento de trabajadores via AJAX, buscando valor retenido y base imponible
	 	 	$('#idTrabajadorMov').change(function(event) {
	 	 		if($(this).val() != ""){
	 	 			calcularExoneracion();
		 	 		event.preventDefault();	
	 	 		}
	 	 	});

});
 	
 	function calcularRetencionMensual() {
 		var impRentaCausado = parseFloat($("#impuestoRentaCausado").val());
			var retenciones = parseFloat($("#retencionOtrosEmpleadores").val()) + parseFloat($("#irAsumidoEmpleadorActual").val());
			var rentencionMensual = (impRentaCausado - retenciones)/parseFloat($("#mesesTrabajados").val());
			$("#retenciones").val(rentencionMensual);
	}
 	
 	function totalGastosPersonales() {
 		var ingresosGravados = (parseFloat($("#sueldosYSalarios").val())*12) +parseFloat($("#sobresueldos").val()) + parseFloat($("#participacionUtilidades").val()) + 
			parseFloat($("#ingresosOtrosEmpleadores").val()) + parseFloat($("#irAsumidoOtrosEmpleadores").val());
		var GP1 = ingresosGravados*50/100;
		var GP2 = 1.3*10800;
 		var totalGastosPersonalesAnual = parseFloat($("#vivienda").val()) + parseFloat($("#salud").val()) + parseFloat($("#educacion").val()) + parseFloat($("#alimentacion").val()) +parseFloat($("#vestimenta").val());
		var totalGastosPersonalesMensual = parseFloat($("#mvivienda").val()) + parseFloat($("#msalud").val()) + parseFloat($("#meducacion").val()) + parseFloat($("#malimentacion").val()) +parseFloat($("#mvestimenta").val());
			
			if(GP1 < GP2){
				$("#totalAnual").attr('max',GP1).val('');
			} else {
				$("#totalAnual").attr('max',GP2).val('');
			}
			$("#totalAnual").val(totalGastosPersonalesAnual);
			$("#totalMensual").val(totalGastosPersonalesMensual);
	}
 	
	 function calcularExoneracion() {
		 var idtrabajador =  $('#idTrabajadorMov').val();
			//calcular exoneracion
			$.ajax({
				url : window.location.pathname + "/exoneracion",
				type : "POST",
				contentType : 'application/json',
				mimeType: 'application/json',
				dataType : 'json',
				data :  {trabajador : idtrabajador},
	
				success : function(response) {
					$("#exoneracionDiscapacidad").val(response[0]);
					$("#exoneracionDiscapacidadMensual").val(response[0]/12);
					$("#exoneracionTerceraE").val(response[1]);
					$("#exoneracionTerceraEMensual").val(response[1]/12);
					// suma de valores de movimientos anteriores del trabajador
					$("#baseImponible").val(response[2]);
					$("#valorRetenido").val(response[3]);
					$("#mesesTrabajados").val(12);
				},
				error : function(xhr, status, error) {
					alert(xhr.responseText);
				}
			});
	}
 
	 function calcularCampo619Anexo104() {
		var sum619 = parseFloat($("#c601").val()) - parseFloat($("#c602").val()) - parseFloat($("#c605").val()) - parseFloat($("#c609").val())
		- parseFloat($("#c611").val()) - parseFloat($("#c612").val()) - parseFloat($("#c613").val());
		if(sum619 > 0){
			$("#c619").val(sum619);
			$("#c699").val(sum619 + parseFloat($("#c621").val()));
			$("#c859").val(parseFloat($("#c699").val()) + parseFloat($("#c801").val()));
			$("#c902").val(parseFloat($("#c859").val()) - parseFloat($("#c898").val()));
		}
	 }
 
 
	function calcularImpuestoRentaCausado() {
			var ingresosGravados = (parseFloat($("#sueldosYSalarios").val())*parseInt($("#mesesTrabajados").val())) +parseFloat($("#sobresueldos").val()) + parseFloat($("#participacionUtilidades").val()) + 
			parseFloat($("#ingresosOtrosEmpleadores").val()) + parseFloat($("#irAsumidoOtrosEmpleadores").val());
			var numMeses = parseInt($("#mesesTrabajados").val());
			
			var totalGastosPersonalesAnual = parseFloat($("#vivienda").val()) + parseFloat($("#salud").val()) + parseFloat($("#educacion").val()) + parseFloat($("#alimentacion").val()) +parseFloat($("#vestimenta").val());
			var totalExoneraciones = parseFloat($("#exoneracionDiscapacidad").val()) + 	parseFloat($("#exoneracionTerceraE").val());
			var gastosDeducibles = (parseFloat($("#iessOtrosEmpleadores").val()) + parseFloat($("#aportePersonalEmpleadorActual").val()))*12 + totalGastosPersonalesAnual + totalExoneraciones;
			var baseImponible  = ingresosGravados - gastosDeducibles;
			
			$("#baseImponibleGravada").val(baseImponible);
		
		// calcular el impuesto a la renta causado via AJAX
			$.ajax({
				url : window.location.pathname + "/impuesto",
				type : "POST",
				contentType : 'application/json',
				mimeType: 'application/json',
				dataType : 'json',
				data :  {baseImponible : baseImponible},
	
				success : function(response) {
					$("#impuestoRentaCausado").val(response);
				},
				error : function(xhr, status, error) {
					alert(xhr.responseText);
				}
			});
	
	}
 
	// calcular porcentaje iva para la vista compra
/*	function calcularPorcentajeIva() {
		// buscar porciento de retención de iva en la compra
			var idProveedor = $("#idProveedorCompra").val();
		$.ajax({
			url : window.location.pathname + "/porcentajeIva",
			type : "POST",
			contentType : 'application/json',
			mimeType: 'application/json',
			dataType : 'json',
			data :  {idProveedor : idProveedor, idTipoCompra : idTipoCompra},
	
			success : function(response) {
				$("#RIret").val(response);
				
			},
			error : function(xhr, status, error) {
				alert(xhr.responseText);
			}
		});
	}
*/	
	
	// buscar el nombre del proveedor para la vista compra
	function BuscarNombreProveedor() {
		var idProveedor = $("#idProveedorCompra").val();
		$.ajax({
			url : window.location.pathname + "/BuscarNombreProveedor",
			type : "POST",
			contentType : 'application/json',
			mimeType: 'application/json',
			dataType : 'json',
			data :  {idProveedor : idProveedor},
	
			success : function(response) {
				$("#nombreProveedor").val(response.response);
			},
			error : function(xhr, status, error) {
				alert(error);
			},
		});
	}
	
	// buscar el nombre del cliente para la vista venta
	function BuscarNombreCliente() {
		var idCliente = $("#idClienteVenta").val();
		$.ajax({
			url : window.location.pathname + "/BuscarNombreCliente",
			type : "POST",
			contentType : 'application/json',
			mimeType: 'application/json',
			dataType : 'json',
			data :  {idCliente : idCliente},
	
			success : function(response) {
				$("#nombreCliente").val(response.response);
			},
			error : function(xhr, status, error) {
				alert(error);
			},
		});
	}
	
	// Upercase
	function mayuscula(campo){
	    $(campo).keyup(function() {
	         $(this).val($(this).val().toUpperCase());
	    });
	}
	
	// lowercase
	function minuscula(campo){
	    $(campo).keyup(function() {
	        $(this).val($(this).val().toLowerCase());
	    });
	}
	
	function calcularPrecioTotalProducto(campo) {
		var subtotal = campo.parent('td').parent('tr').find('input#subTotal');
		var descuento = campo.parent('td').parent('tr').find('input#tDescuento');
		//var cantidad = campo.parent('td').parent('tr').find('input#cantidad');

		var descontar = (subtotal.val() * descuento.val())/100;
		var total = parseFloat(subtotal.val()) - parseFloat(descontar);
		campo.parent('td').parent('tr').find('input#precioTotal').val(parseFloat(total).toFixed(3));
		calcularPrecioTotalVenta();
	}
	
	function calcularPrecioTotalVenta() {
		//poniendo el valor del campo subtotal de la fila base oculta
	//	$(".fila-base input#precioTotal").val(0);
		var totalVenta = 0;
		$('.precioTotal').each(function(){
			totalVenta += parseFloat($(this).val());
	     });
		$("#totalVenta").val(parseFloat(totalVenta).toFixed(3));
		
	}
	
	function calcularSubTotalProducto(campo) {
		var precioUnitario = campo.parent('td').parent('tr').find('input#precioUnitario');
		var cantidad = campo.parent('td').parent('tr').find('input#cantidad');
		var subtotal =	precioUnitario.val() * cantidad.val();
		campo.parent('td').parent('tr').find('input#subTotal').val(parseFloat(subtotal).toFixed(3));
	}

	function ButtonAdicionarProductosVenta() {
		$("#adicionar").on('click', function(event){
			var codP = $("#codP").val();
			var codA = $("#codA").val();
			event.preventDefault();
			var responseValue;

			$.ajax({
				url : window.location.pathname + "/precioProducto",
				type : "POST",
				contentType : 'application/json',
				mimeType: 'application/json',
				//dataType : 'json',
				data :  {codP : codP, codA : codA},

				success : function(response) {
					$(".fila-base input#precioUnitario").val(response);
					$(".fila-base input#codigoPrincipalProducto").val(codP);
					$(".fila-base input#codigoAuxiliarProducto").val(codA);
					$(".fila-base input#precioTotal").val(response);
					$(".fila-base input#subTotal").val(response);
					calcularPrecioTotalVenta();

					$("#tablaVenta tbody tr:eq(0)").clone().removeClass('fila-base hidden').appendTo("#tablaVenta tbody");
					setTimeout("setup.bindEvents();",300);
				},
				error : function(xhr, status, error) {
					alert("No se encontró el precio del producto agregado");
				}
			});
			
		});
	}
	
	function ButtonAdicionarDetalleCompra() {
		$("#adicionarDetalle").on('click', function(event){
			$("#tablaDetallesCompra tbody tr:eq(0)").clone().removeClass('fila-base').appendTo("#tablaDetallesCompra tbody");
			//setTimeout("setup.bindEvents();",300);
			cambiarValoresFilasDetalleClonadas();
		});
	}
	
	function ButtonEliminarFila() {
		$(document).on("click","#eliminar-fila",function(){
			var $buttonsEliminarfila = $('td#eliminar-fila');
			if($buttonsEliminarfila.size()==1){
				alert("No se puede eliminar esta fila");
			} else{
				var parent = $(this).parents().get(0);
				$(parent).remove();
			}
		});
	}
	
	function cambiarValoresFilasDetalleClonadas() {
		var $idDetalleCompra = $("tr:last input#idDetalleCompra");
		var $baseNoObjetoIva = $("tr:last input#baseNoObjetoIva");
		var $baseImpobible0 = $("tr:last input#baseImpobible0");
		var $baseImponible12 = $("tr:last input#baseImponible12");
		var $RIRbaseNoObjIva = $("tr:last input#RIRbaseNoObjIva");
		var $RIRbaseImp0 = $("tr:last input#RIRbaseImp0");
		var $RIRbaseImp12 = $("tr:last input#RIRbaseImp12");
		var $RIRret = $("tr:last input#RIRret");
		var $RIRretIr = $("tr:last input#RIRretIr");
		var $RIvalorIva = $("tr:last input#RIvalorIva");
		var $RIret = $("tr:last input#RIret");
		var $RIretIva = $("tr:last input#RIretIva");
		
		$idDetalleCompra.val(0);
		$baseNoObjetoIva.val(0);
		$baseImpobible0.val(0);
		$baseImponible12.val(0);
		$RIRbaseNoObjIva.val(0);
		$RIRbaseImp0.val(0);
		$RIRbaseImp12.val(0);
		$RIRret.val(0);
		$RIRretIr.val(0);
		$RIvalorIva.val(0);
		$RIret.val(0);
		$RIretIva.val(0);
		
	}
	
	function calcularBaseNoObjetoIva(campo) {
		var RIRbaseNoObjIva = campo.parent('td').parent('tr').find('input#RIRbaseNoObjIva');
		RIRbaseNoObjIva.val(campo.val());
	}
	
	function calcularRIRbaseImp0(campo) {
		var RIRbaseImp0 = campo.parent('td').parent('tr').find('input#RIRbaseImp0');
		RIRbaseImp0.val(campo.val());
	}
	
	function calcularRIRbaseImp12(campo) {
		var RIRbaseImp12 = campo.parent('td').parent('tr').find('input#RIRbaseImp12');
		RIRbaseImp12.val(campo.val());
		
		var RIvalorIva = campo.parent('td').parent('tr').find('input#RIvalorIva');
		var porcentajeIva = campo.parent('td').parent('tr').find('input#porcentajeIva');
		// calcular valor IVA
		var valorIva = (campo.val()*porcentajeIva.val())/100;
		RIvalorIva.val(valorIva);
		
		// calcular rentencionIva
		var RIretIva = campo.parent('td').parent('tr').find('input#RIretIva');
		var RIret = campo.parent('td').parent('tr').find('input#RIret');
		var retIva = (valorIva*RIret.val())/100;
		RIretIva.val(retIva);
		
		//calcular retencionIR
		var RIRretIr = campo.parent('td').parent('tr').find('input#RIRretIr');
		var RIRret = campo.parent('td').parent('tr').find('input#RIRret');
		var retIR = (RIRbaseImp12.val()*RIRret.val())/100;
		RIRretIr.val(retIR);
		
		//total retenido
		calcularTotalRetenido();
	}
	
	function calcularTotalDocumento() {
		var arrBaseNoObjetoIva = $('input#baseNoObjetoIva');
		var arrbaseImp0 = $('input#baseImpobible0');
		var arrbaseImp12 = $('input#baseImponible12');
		var arrRIvalorIva = $('input#RIvalorIva');
		var iceImpuesto = $("#iceImpuesto");
		var otrosImpuestos = $("#otrosImpuestos");
		
		this.totalDocumento = 0;
		for (var int = 0; int < arrBaseNoObjetoIva.length; int++) {
			this.totalDocumento += parseFloat(arrBaseNoObjetoIva[int].value) + parseFloat(arrbaseImp0[int].value) + parseFloat(arrbaseImp12[int].value) + parseFloat(arrRIvalorIva[int].value);
		}
		
		if(iceImpuesto.val() != ""){
			this.totalDocumento += parseFloat(iceImpuesto.val());
		}
		
		if(otrosImpuestos.val() != ""){
			this.totalDocumento += parseFloat(otrosImpuestos.val());
		}
		
	//	var redondeado = Math.round(this.totalDocumento*100)/100;
	//	alert("redondeado "+ redondeado);
		$("#totalDocumento").val(this.totalDocumento);
		
		calcularValorAPagar(this.totalDocumento);
	 	desabilitarTipoPago(this.totalDocumento);	
	}

	function calcularValorAPagar(totalDocumento) {
		var valorPagar = totalDocumento - parseFloat($("#totalRet").val());
		$("#valorAPagar").val(valorPagar);
	}
	
	function desabilitarTipoPago(totalDocumento) {
	 	//deshabilitar el tipo de pago si el totalDocumento es < 1000 
		var $tipoPago = $('#tipoPago');
			if (totalDocumento >= 1000) {
				$tipoPago.removeAttr('disabled');
			} else {
				$tipoPago.attr('disabled', 'disabled').val('');
			}
	}
	
	function calcularTotalRetenido() {
		var arrRIRretIr = $('input#RIRretIr');
		var arrRIretIva = $('input#RIretIva');
		this.totalRet = 0;
		
		for (var int = 0; int < arrRIRretIr.length; int++) {
			this.totalRet += parseFloat(arrRIRretIr[int].value) + parseFloat(arrRIretIva[int].value);
		}
		
		$("#totalRet").val(this.totalRet);
		
		var valorPagar = parseFloat($("#totalDocumento").val()) + parseFloat($("#totalRet").val());
		$("#valorAPagar").val(valorPagar);
	}


	$('#tipoIdentificacion').change(function() { 
		if ($(this).val() != '06'){
			$('#idTipoProveedorOCliente').attr('disabled', 'disabled').val('');
		}
		else{

			$('#idTipoProveedorOCliente').removeAttr('disabled');	
		}
	}).trigger('change');  
