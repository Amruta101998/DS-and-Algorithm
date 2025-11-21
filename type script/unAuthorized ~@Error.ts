import { Inject, Optional } from "@angular/core";
import { LocalStorageService } from "@core/services/local-storage.service";
import { ModalService } from "src/app/shared/services/modal.service";
import { ModalConstants } from "src/app/utils/modals-constants";
export class UnAuthorizedError extends Error {
    private static readonly ERROR_NAME = 'UnauthorizedError';
    private static readonly DEFAULT_MESSAGE = 'User session has expired';
    private static readonly MODAL_SELECTOR = '[data-modal="session-expire"]';
    constructor(
        @Optional() @Inject(ModalService) private readonly _modalService: ModalService,
        @Optional() @Inject(LocalStorageService) private readonly _localStorageService: LocalStorageService,
        message: string = UnAuthorizedError.DEFAULT_MESSAGE
    ) {̦
        super(message);
        this.name = UnAuthorizedError.ERROR_NAME;
        this.handleUnauthorizedError().catch(this.handleError.bind(this));
    }
    private async handleUnauthorizedError(): Promise<void> {
        try {
            if (await this.isModalAlre̦adyOpen()) {
                return;
            }
            const modalResponse = await this._modalService.openModalConfirm(
                this._modalService.createModalBody(ModalConstants.SESSION_EXPIRE)
            );
            if (modalResponse !== ModalConstants.MODAL_RESPONSES.SUCCESS) {
                console.warn(`Session expire modal response: ${modalResponse}`);
                return;
            }
            await this.logout();
        } catch (error) {
            this.handleError(this.createErrorObject(error));
        }
    }
    private async isModalAlreadyOpen(): Promise<boolean> {
        return !!document.querySelector('[data-modal="session-expire"]');
    }
    private async showSessionExpireModal(): Promise<string> {
        return this._modalService.openModalConfirm(
            this._modalService.createModalBody(ModalConstants.SESSION_EXPIRE)
        );
    }
    private isSuccessResponse(response: string): boolean {
        if (response !== ModalConstants.MODAL_RESPONSES.SUCCESS) {
            console.warn(`Session expire modal response: ${response}`);
            return false;
        }
        return true;
    }
    private async logout(): Promise<void> {
        try {
            await this._localStorageService.onLogout.next(true);
        } catch (error) {
            this.handleError({
                name: 'LogoutError',
                message: 'Failed to perform logout operation',
                originalError: error
            });
        }
    }
    private handleError(error: unknown): void {
        console.error('UnauthorizedError handling failed:', error);
        // Optionally integrate with an error tracking service
    }
}
            }
            await this.logout();
        } catch (error) {
            this.handleError(error);
        }
        this._localStorageService.onLogout.next(true);
    }
}