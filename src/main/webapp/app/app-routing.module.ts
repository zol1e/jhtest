import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { errorRoute } from './layouts';
import { navbarRoute } from './layouts2';
import { DEBUG_INFO_ENABLED } from 'app/app.constants';
import { musicappRoute } from 'app/musicapp/musicapp.route';

const LAYOUT_ROUTES = [navbarRoute, musicappRoute, ...errorRoute];

@NgModule({
    imports: [
        RouterModule.forRoot(
            [
                ...LAYOUT_ROUTES,
                {
                    path: 'admin',
                    loadChildren: './admin/admin.module#JhtestAdminModule'
                }
            ],
            { useHash: true, enableTracing: DEBUG_INFO_ENABLED }
        )
    ],
    exports: [RouterModule]
})
export class JhtestAppRoutingModule {}
