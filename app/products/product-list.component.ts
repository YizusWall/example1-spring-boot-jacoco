import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';

import { fadeInAnimation } from '../_animations/index';
import { NormaService, PubSubService } from '../_services/index';

@Component({
    moduleId: module.id.toString(),
    templateUrl: 'product-list.component.html',
    animations: [fadeInAnimation],
    host: { '[@fadeInAnimation]': '' }
})

export class NormaListComponent implements OnInit, OnDestroy {
    normas: any[];
    subscription: Subscription;

    constructor(
        private normaService: NormaService,
        private pubSubService: PubSubService) { }
    
    deleteNorma(id: number) {
        this.normaService.delete(id);
        this.loadNormas();
    }

    ngOnInit() {
        this.loadNormas();

        // reload products when updated
        this.subscription = this.pubSubService.on('products-updated').subscribe(() => this.loadNormas());
    }

    ngOnDestroy() {
        // unsubscribe to ensure no memory leaks
        this.subscription.unsubscribe();
    }

    private loadNormas() {
        this.normas = this.normaService.getAll();
    }
}