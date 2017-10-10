import { Injectable } from '@angular/core';

@Injectable()
export class NormaService {
    getAll() {
        return this.getNormas();
    }

    getById(id: number) {
        return this.getNormas().find(norma => norma.id === id);
    }

    save(norma: any) {
        let normas = this.getNormas();

        if (norma.id) {
            // update existing product

            for (var i = 0; i < normas.length; i++) {
                if (normas[i].id === norma.id) {
                    normas[i] = norma;
                    break;
                }
            }
            this.setNormas(normas);
        } else {
            // create new product

            // assign id
            var lastNorma = normas[normas.length - 1] || { id: 0 };
            norma.id = lastNorma.id + 1;

            // save to local storage
            normas.push(norma);
            this.setNormas(normas);
        }
    }

    delete(id: number) {
        let normas = this.getNormas();
        for (var i = 0; i < normas.length; i++) {
            var norma = normas[i];
            if (norma.id === id) {
                normas.splice(i, 1);
                break;
            }
        }
        this.setNormas(normas);
    }

    // private helper methods

    private getNormas(): any[] {
        if (!localStorage.getItem('normas')) {
            localStorage.setItem('normas', JSON.stringify([]));
        }

        return JSON.parse(localStorage.getItem('normas'));
    }

    private setNormas(normas: any[]) {
        localStorage.setItem('normas', JSON.stringify(normas));
    }
}