import { Component } from '@angular/core';

import { NormaService } from './_services/index';

@Component({
    moduleId: module.id.toString(),
    selector: 'app',
    templateUrl: 'app.component.html'
})

export class AppComponent {
    constructor(
      private normaService: NormaService
    ) {
        // add some initial products
        if (normaService.getAll().length === 0) {
            normaService.save({ TipoNorma: 'tiponorma', CodigoNorma: '343434343',CodigoGestionNorma:'111111',TituloNorma:'',RegistroResponsable:'',FechaVigencia:'',RegistroNorma:'' });
            normaService.save({ TipoNorma: 'tipon2', CodigoNorma: '343434343',CodigoGestionNorma:'111',TituloNorma:'',RegistroResponsable:'' ,FechaVigencia:'', RegistroNorma:''});
            normaService.save({ TipoNorma: 'tiponorma', CodigoNorma: '343434343',CodigoGestionNorma:'1111',TituloNorma:'',RegistroResponsable:'',FechaVigencia:'', RegistroNorma:'' });
        }
    }
}