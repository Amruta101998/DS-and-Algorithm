import { Inject, Optional } from "@angular/core";
import { LocalStorageService } from "@core/services/local-storage.service";
import { ModalService } from "src/app/shared/services/modal.service";
import { ModalConstants } from "src/app/utils/modals-constants";

export class UnAuthorizedError {

    constructor(@Optional() @Inject(ModalService) private _modalService: ModalService,
    @Optional() @Inject(LocalStorageService) private _localStorageService: LocalStorageService) {
        this.handleUnauthorizedError();
    }

    async handleUnauthorizedError() {
        console.log('inside handle unauthorized error');

        let modalElement = document.getElementById('confirmation-session-expire') as HTMLElement;
        if (modalElement) {
            return;
        }
        const modalResponse_ = await this._modalService.openModalConfirm(
            this._modalService.createModalBody(ModalConstants.SESSION_EXPIRE));
        if (modalResponse_ !== ModalConstants.MODAL_RESPONSES.SUCCESS) {
            console.log("returning on response from modal:" + modalResponse_);
            return;
        }
        this._localStorageService.onLogout.next(true);
    }
}