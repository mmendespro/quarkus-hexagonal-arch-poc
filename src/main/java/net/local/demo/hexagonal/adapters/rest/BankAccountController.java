package net.local.demo.hexagonal.adapters.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.local.demo.hexagonal.adapters.rest.request.AccountBalanceResponse;
import net.local.demo.hexagonal.adapters.rest.request.AccountRequest;
import net.local.demo.hexagonal.application.domain.entities.Account;
import net.local.demo.hexagonal.application.ports.incoming.CheckBalanceUseCase;
import net.local.demo.hexagonal.application.ports.incoming.CreateAccountUseCase;
import net.local.demo.hexagonal.application.ports.incoming.DepositUseCase;
import net.local.demo.hexagonal.application.ports.incoming.TransferUseCase;
import net.local.demo.hexagonal.application.ports.incoming.WithdrawUseCase;

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BankAccountController {
    
    private final DepositUseCase depositUseCase;
    private final WithdrawUseCase withdrawUseCase;
    private final CreateAccountUseCase createAccountUseCase;
    private final CheckBalanceUseCase checkBalanceUseCase;
    private final TransferUseCase transferUseCase;

    public BankAccountController(CreateAccountUseCase createAccountUseCase, DepositUseCase depositUseCase, WithdrawUseCase withdrawUseCase, CheckBalanceUseCase checkBalanceUseCase, TransferUseCase transferUseCase) {
        this.createAccountUseCase = createAccountUseCase;
        this.depositUseCase = depositUseCase;
        this.withdrawUseCase = withdrawUseCase;
        this.checkBalanceUseCase = checkBalanceUseCase;
        this.transferUseCase = transferUseCase;
    }

    @GET
    @Path("{id}") 
    public Response checkBalance(@PathParam("id") Long id) {
        Account account = checkBalanceUseCase.checkBalance(id);
        AccountBalanceResponse response = new AccountBalanceResponse(account.getBalance().getValue(), account.getTransactions());
        return Response.ok(response).build(); 
    }

    @POST
    public Response createAccount(AccountRequest request){
        createAccountUseCase.create(request.getId(), request.getBalance());
        return Response.ok(request).status(201).build();  
    }

    @PUT
    @Path("{id}/deposit/{amount}") 
    public Response depositAmount(@PathParam("id") Long id, @PathParam("amount") float amount){
        depositUseCase.deposit(id, amount);
        return Response.ok().status(204).build();  
    }

    @PUT
    @Path("{id}/withdraw/{amount}") 
    public Response withdrawAmount(@PathParam("id") Long id, @PathParam("amount") float amount){
        withdrawUseCase.withdraw(id, amount);
        return Response.ok().status(204).build();  
    }

    @PUT
    @Path("{fromId}/transfer/{toId}/{amount}") 
    public Response transferAmount(@PathParam("fromId") Long fromId, @PathParam("toId") Long toId, @PathParam("amount") float amount){
        transferUseCase.transfer(fromId, toId, amount);
        return Response.ok().status(204).build();  
    }
}