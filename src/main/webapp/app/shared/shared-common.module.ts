import { NgModule } from '@angular/core';

import { JhtestSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [JhtestSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [JhtestSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class JhtestSharedCommonModule {}
