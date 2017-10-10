import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { slideInOutAnimation } from '../_animations/index';
import { NormaService, PubSubService } from '../_services/index';
import {tipoNorma} from './tipoNorma';
import {registroResponsable} from './registroResponsable';
@Component({
    moduleId: module.id.toString(),
    templateUrl: 'product-add-edit.component.html',
    animations: [slideInOutAnimation],
    host: { '[@slideInOutAnimation]': '' }
})

export class ProductAddEditComponent implements OnInit {
    title = "Agregar Normas";
    norma: any = {};
   

   
  
    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private NormaService: NormaService,
        private pubSubService: PubSubService) { }
      
      
        tipoNormas = [
            new tipoNorma(0,'Seleccione Normas'),
            new tipoNorma(1, 'Decreto Supremo' ),
           new tipoNorma(2, 'Decreto Legislativo' ),
           new tipoNorma(3, 'Circular' ),
           new tipoNorma(4, 'Resolucion' ),
           new tipoNorma(5, 'Disposiciones'),
           new tipoNorma(6, 'Otros')
        ];

        registroResponsables=[
           new registroResponsable(0,'Seleccione Registro Responsable'),
           new registroResponsable(1,'Gerencias'),
           new registroResponsable(2,'Departamentos'),
           new registroResponsable(3,'Comites'),
           new registroResponsable(4,'Otros')
        ];
       
       
    ngOnInit() {
        let normaId = Number(this.route.snapshot.params['id']);
        this.norma.TipoNorma=this.tipoNormas[0].nombre;
        this.norma.RegistroResponsable=this.registroResponsables[0].nombre;
        if (normaId) {
            this.title = 'Editar norma';
            this.norma = this.NormaService.getById(normaId);
        }
      
        
    }

    guardarNorma() {
        // save product
      
        alert
        this.NormaService.save(this.norma);
     
        // redirect to users view
        this.router.navigate(['/normas']);

        // publish event so list controller can refresh
		console.log("publicando");
        this.pubSubService.publish('products-updated');
        
    
    
    }
}